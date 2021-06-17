package com.assignment.library.model.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
