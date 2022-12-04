package vn.codingt.clean.rsql;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import vn.codingt.clean.rsql.annotion.Hidden;
import vn.codingt.clean.rsql.dto.DataPage;
import vn.codingt.clean.rsql.exception.RSqlMappingException;
import vn.codingt.clean.rsql.path.JavaPropertyPath;
import vn.codingt.clean.rsql.sort.SortBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class JpaRSqlProcessor<T> {

    private final JpaSpecificationExecutor<T> reposition;

    public JpaRSqlProcessor(@Nonnull JpaSpecificationExecutor<T> repo) {
        this.reposition = repo;
    }

    public static <T> DataPage<T> execQuery(JpaSpecificationExecutor<T> reposition,
                                            String selectFields, String filter,
                                            int size, int page, String sort) {
        return new JpaRSqlProcessor<>(reposition).execQuery(selectFields, filter, size, page, sort);
    }

    public DataPage<T> execQuery(@Nonnull String selectFields, @Nullable  String filter, int size, int page, @Nullable String sort) {

        Specification<T> spec = JpaRSqlSpecBuilder.build(filter, sort);

        if (!ObjectUtils.isEmpty(sort)) {
            spec = spec.and(new SortBuilder<>(sort));
        }

        PageRequest pageRequest = PageRequest.of(page-1, size);

        Page<T> result = reposition.findAll(spec, pageRequest);

        long totalElements = result.getTotalElements();
        int totalPage = result.getTotalPages();

        JavaPropertyPath expandedPath = parseSelectedFields(selectFields);
        List<Map<String, Object>> mappedEntities = mapEntities(result.getContent(), expandedPath);

        return new DataPage<>(
                mappedEntities,
                totalElements,
                totalPage,
                result.isFirst(),
                result.isLast(),
                result.getSize(),
                result.getNumberOfElements(),
                result.getNumber()
        );
    }

    private List<Map<String, Object>> mapEntities(Collection<?> entities, @Nullable JavaPropertyPath expandedPath) {
        List<Map<String, Object>> records = new ArrayList<>(entities.size());
        for (Object entity : entities) {
            Map<String, Object> propertMap = new TreeMap<>();
            records.add(propertMap);
            if (expandedPath != null) {
                mapEntity(propertMap, entity, expandedPath.getSubPaths());
            } else {
                mapEntity(propertMap, entity, new ArrayList<>());
            }
        }

        return records;
    }

    private void mapEntity(@Nonnull Map<String, Object> propertMap,
                           @Nonnull Object entity,
                           @Nonnull List<JavaPropertyPath> expandedFields) {
        ReflectionUtils.doWithFields(entity.getClass(), field -> {
            try {

                if (isIgnoreField(field)) {
                    return;
                }
                if (isDbColumn(field) && (expandedFields.isEmpty() || isSelectedDbField(field, expandedFields))) {
                    Method getter = findGetter(entity.getClass(), field);
                    if (getter != null) {
                        Object value = getter.invoke(entity);
                        if (value != null) {
                            propertMap.put(field.getName(), value);
                        }
                    }
                } else if (isRelationalColumn(field) && isSelectedDbField(field, expandedFields)) {
                    Method getter = findGetter(entity.getClass(), field);
                    if (getter != null) {
                        Object value = getter.invoke(entity);
                        if (value != null) {
                            JavaPropertyPath subPath = findSubPath(expandedFields, field.getName());
                            if (value instanceof Collection) {
                                List<Map<String, Object>> list = mapEntities((Collection<?>) value, subPath);
                                propertMap.put(field.getName(), list);
                            } else {
                                TreeMap<String, Object> subProperties = new TreeMap<>();
                                propertMap.put(field.getName(), subProperties);
                                if (subPath != null && subPath.hasSubPath()) {
                                    mapEntity(subProperties, entity, subPath.getSubPaths());
                                } else {
                                    mapEntity(subProperties, entity, new ArrayList<>());
                                }
                            }
                        }
                    }
                }


            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new RSqlMappingException(e);
            }
        });
    }

    private JavaPropertyPath findSubPath(List<JavaPropertyPath> expandedFields, String name) {
        for (JavaPropertyPath path : expandedFields) {
            if (path.getName().equals(name)) {
                return path;
            }
        }
        return null;
    }

    private boolean isRelationalColumn(@Nonnull Field field) {
        return field.getAnnotation(OneToMany.class) != null ||
                field.getAnnotation(ManyToMany.class) != null ||
                field.getAnnotation(ManyToOne.class) != null ||
                field.getAnnotation(OneToOne.class) != null;

    }

    private Method findGetter(Class<?> aClass, Field field) {
        String capitalize = StringUtils.capitalize(field.getName());
        String getter = "get" + capitalize;
        return ReflectionUtils.findMethod(aClass, getter);
    }

    private boolean isSelectedDbField(Field field, JavaPropertyPath expandedFields) {
        return expandedFields.getName().equals(field.getName());
    }

    private boolean isSelectedDbField(Field field, List<JavaPropertyPath> expandedFields) {
        for (JavaPropertyPath path : expandedFields) {
            if (isSelectedDbField(field, path)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDbColumn(Field field) {
        if(field.getAnnotation(Id.class) != null){
            return true;
        }
        return field.getAnnotation(Column.class) != null;
    }

    private boolean isIgnoreField(@Nonnull Field field) {
        return field.getAnnotation(Hidden.class) != null;
    }

    private JavaPropertyPath parseSelectedFields(String fieldsStr) {
        if (fieldsStr == null || fieldsStr.trim().isEmpty()) {
            return null;
        }

        JavaPropertyPath root = new JavaPropertyPath("");

        String[] fields = fieldsStr.trim().split("\\s*,\\s*");

        for (String field : fields) {
            if (field.trim().isEmpty()) {
                continue;
            }
            String[] pathArr = field.split("\\.");
            JavaPropertyPath parentPath = root;
            for (String property : pathArr) {
                if (property.trim().isEmpty()) {
                    continue;
                }
                JavaPropertyPath subPath = parentPath.getSubPath(property);
                if (subPath == null) {
                    subPath = new JavaPropertyPath(property);
                    parentPath.addSubPath(subPath);
                }
                parentPath = subPath;
            }
        }

        return root;
    }

}
