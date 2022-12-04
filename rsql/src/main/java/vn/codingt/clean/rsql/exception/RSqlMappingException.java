package vn.codingt.clean.rsql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class RSqlMappingException extends RuntimeException {

    private static final long serialVersionNumber = 1L;

    public RSqlMappingException(Exception e){
        super(e);
    }
}
