package com.assignment.library.model.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
