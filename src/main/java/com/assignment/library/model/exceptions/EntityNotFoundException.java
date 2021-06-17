package com.assignment.library.model.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
