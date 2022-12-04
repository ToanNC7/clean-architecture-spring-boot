package vn.codingt.clean.rsql.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class DataPage<T> {

    private T swaggerMode;
    private List<Map<String, Object>> records;
    private long count;
    private long totalElements;
    private long totalPage;

    private int number;

    private int page;
    private boolean first;
    private boolean last;
    private int size;
    private int numberOfElements;

    public DataPage(List<Map<String, Object>> records,
                    long totalElements, int totalPage, boolean first, boolean last,
                    int size, int numberOfElements, int number){
        this.records = records;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
        this.first = first;
        this.last = last;
        this.size = size;
        this.numberOfElements = numberOfElements;
        this.number = number;
    }
}
