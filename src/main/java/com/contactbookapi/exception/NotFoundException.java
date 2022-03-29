package com.contactbookapi.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception containing relevant information for NOT FOUND API errors.
 *
 * @author Samuel Stalschus
 */
public class NotFoundException extends ApiException{
    private static final long serialVersionUID = -525546673970530803L;

    private static final String NOT_FOUND_CODE = "not_found";

    /**
     * Creates a new instance, with provided cause.
     *
     * @param e cause
     */
    public NotFoundException(Throwable e) {
        super(
                NOT_FOUND_CODE,
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND.value(),
                e);
    }

    /**
     * Creates a new instance, with provided cause and custom message.
     *
     * @param message custom message.
     * @param e cause.
     */
    public NotFoundException(String message, Throwable e) {
        super(NOT_FOUND_CODE, message, HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
    }

    /**
     * Creates a new instance, with provided cause and custom message.
     *
     * @param message custom message.
     */
    public NotFoundException(String message) {
        super(NOT_FOUND_CODE, message, HttpStatus.NOT_FOUND.value());
    }
}
