package vn.codingt.clean.data.db.jpa.entities;

import vn.codingt.clean.core.domain.Identity;

public final class IdConverter {

    public static Long converterId(Identity id) {
        if (id != null && id.getNumber() != Long.MIN_VALUE) {
            return id.getNumber();
        }
        return null;
    }

}
