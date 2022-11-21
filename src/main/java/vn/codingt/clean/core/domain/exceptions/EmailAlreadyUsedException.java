package vn.codingt.clean.core.domain.exceptions;

public class EmailAlreadyUsedException extends DomainException {

    public EmailAlreadyUsedException(String message) {
        super(message);
    }

}
