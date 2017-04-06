package com.teguh.jpa.crud.example.domain;

/**
 * Thrown if there is an error when validating an entity.
 */
public class EntityException extends Exception {
    private static final long serialVersionUID = 8620489894045929496L;

    public EntityException() {
        super();
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityException(Throwable cause) {
        super(cause);
    }

}
