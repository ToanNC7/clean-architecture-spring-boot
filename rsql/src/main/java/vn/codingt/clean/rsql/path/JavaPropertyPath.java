package vn.codingt.clean.rsql.path;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@ToString
@Getter
public class JavaPropertyPath {

    private final String name;
    private final List<JavaPropertyPath> subPaths;

    public JavaPropertyPath(String name) {
        this.name = name;
        this.subPaths = new ArrayList<>();
    }

    public JavaPropertyPath getSubPath(String subPath){
        for(JavaPropertyPath path : subPaths){
            if(path.name.equals(subPath)){
                return  path;
            }
        }
        return null;
    }

    public void addSubPath(JavaPropertyPath subPath){subPaths.add(subPath);}

    public boolean hasSubPath(){return !subPaths.isEmpty();}
}
