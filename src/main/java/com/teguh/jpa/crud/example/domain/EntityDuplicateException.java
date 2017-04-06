package com.teguh.jpa.crud.example.domain;

/**
 * Thrown if an entity already exist in the database to prevent duplication.
 */
public class EntityDuplicateException extends EntityException {
    private static final long serialVersionUID = -6209245104870776771L;

    public EntityDuplicateException() {
        super();
    }

    public EntityDuplicateException(String s) {
        super(s);
    }

    public EntityDuplicateException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EntityDuplicateException(Throwable cause) {
        super(cause);
    }

}
