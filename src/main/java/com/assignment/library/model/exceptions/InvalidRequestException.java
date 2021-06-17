package com.assignment.library.model.exceptions;

/**
 * @author shubham sharma
 *         <p>
 *         22/05/21
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
