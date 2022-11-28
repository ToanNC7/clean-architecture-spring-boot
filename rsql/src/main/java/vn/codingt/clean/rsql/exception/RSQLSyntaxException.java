package vn.codingt.clean.rsql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RSQLSyntaxException extends RuntimeException {

    private static final long serialVersionUID = 1l;

    public RSQLSyntaxException(String message, Throwable e){
        super(message, e);
    }
    public RSQLSyntaxException(String message){
        super(message);
    }
}
