package vn.codingt.clean.core.domain.exceptions;

public class NotFoundException extends DomainException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }

}
