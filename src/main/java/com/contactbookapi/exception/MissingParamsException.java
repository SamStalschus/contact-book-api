package com.contactbookapi.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception containing relevant information for missing params API errors.
 *
 * @author Samuel Stalschus
 */
public class MissingParamsException extends ApiException {

    private static final long serialVersionUID = -525546673970530803L;

    private static final String BAD_REQUEST_CODE = "bad_request";

    /**
     * Creates a new instance, with provided cause.
     *
     * @param e cause
     */
    public MissingParamsException(Throwable e) {
        super(
                BAD_REQUEST_CODE,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                e);
    }

    /**
     * Creates a new instance, with provided cause and custom message.
     *
     * @param message custom message.
     * @param e cause.
     */
    public MissingParamsException(String message, Throwable e) {
        super(BAD_REQUEST_CODE, message, HttpStatus.BAD_REQUEST.value(), e);
    }

    /**
     * Creates a new instance, with provided cause and custom message.
     *
     * @param message custom message.
     */
    public MissingParamsException(String message) {
        super(BAD_REQUEST_CODE, message, HttpStatus.BAD_REQUEST.value());
    }
}
