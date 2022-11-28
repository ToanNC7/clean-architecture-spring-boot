package vn.codingt.clean.rsql.path;

import org.hibernate.query.criteria.internal.path.PluralAttributePath;
import org.hibernate.query.criteria.internal.path.SingularAttributePath;

import javax.persistence.Column;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;

public class PathParser {

    public <T> SqlPath parseSelector(Root<T> root, String selector){

        String[] pathComponents = selector.split("\\.");
        String firstPath = pathComponents[0];
        Path path = root.get(firstPath);

        From lastFrom = root;

        for(int i= 1; i< pathComponents.length - 1; i++){
            String pathComponent  = pathComponents[i];

            if(path instanceof SingularAttributePath){
                JsonPath jsonPath = getJsonPath((SingularAttributePath)path, i, pathComponents);
                if(jsonPath != null){
                    return jsonPath;
                }
            }

            if(path instanceof PluralAttributePath){
                PluralAttribute att = ((PluralAttributePath) path).getAttribute();
                Join join = getJoin(att, lastFrom);
                if(join == null){
                    throw new IllegalArgumentException("");
                }
                path = join.get(pathComponent);
                lastFrom = join;
            }
            else if(path instanceof SingularAttribute){
                SingularAttribute attr= ((SingularAttributePath) path).getAttribute();
                if(attr.getPersistentAttributeType() != Attribute.PersistentAttributeType.BASIC){
                    Join join = lastFrom.join(attr, JoinType.LEFT);
                    path = join.get(pathComponent);
                    lastFrom = join;
                }
            }
            else {
                path = path.get(pathComponent);
            }
        }
        return new DefaultSqlPath(path);
    }

    private Join getJoin(PluralAttribute att, From from) {
        switch (att.getCollectionType()){
            case COLLECTION:
                return from.join((CollectionAttribute) att);
            case SET:
                return from.join((SetAttribute) att);
            case LIST:
                return from.join((ListAttribute) att);
            case MAP:
                return from.join((MapAttribute) att);
            default:
                return null;
        }
    }

    private JsonPath getJsonPath(SingularAttributePath path, int i, String[] pathComponent) {
        SingularAttribute attributePath = path.getAttribute();

        Member javaMember = attributePath.getJavaMember();

        if(!(javaMember instanceof Field)){
            return  null;
        }
        Annotation[] declaredAnnotations = ((Field) javaMember).getDeclaredAnnotations();

        Annotation jsonbColumn = null;

        JsonType jsonType = JsonType.JSONB;

        for(Annotation declareAnnotation : declaredAnnotations){
            Class<? extends Annotation> aClass = declareAnnotation.annotationType();
            if(aClass.equals(Column.class)){
                Column column = (Column) declareAnnotation;
                String columnDefinition = column.columnDefinition() != null ?
                        column.columnDefinition().toLowerCase() : new String();

                if(columnDefinition.contains(JsonType.JSONB.getColumnType())){
                    jsonbColumn = column;
                    jsonType = JsonType.JSONB;
                }
                else if(columnDefinition.contains(JsonType.JSON.getColumnType())){
                    jsonbColumn = column;
                    jsonType = JsonType.JSON;
                    break;
                }

            }
        }

        if(jsonbColumn == null){
            return null;
        }
        String[] jsonPathComponents = new String[pathComponent.length - i];
        System.arraycopy(pathComponent, i, jsonPathComponents, 0, jsonPathComponents.length);
        return new JsonPath(path, jsonPathComponents, jsonType);
    }
}
