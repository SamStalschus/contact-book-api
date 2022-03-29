package com.contactbookapi.exception.handler;

import com.contactbookapi.dto.ErrorMessageDTO;
import com.contactbookapi.dto.InvalidParamsDTO;
import com.contactbookapi.exception.ContactAlreadyExistsException;
import com.contactbookapi.exception.MissingParamsException;
import com.contactbookapi.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler for exception handling and return of parameterized messages.
 *
 * @author Samuel Stalschus
 */
@RestControllerAdvice
public class AppExceptionHandler {

    /**
     * The Message source.
     */
    @Autowired
    MessageSource messageSource;

    /**
     * Handle validation exceptions invalid params dto.
     *
     * @param ex the ex
     * @return the invalid params dto
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public InvalidParamsDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errors.put(fieldName, errorMessage);
        });
        return InvalidParamsDTO.builder()
                .message("Invalid params")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .arguments(errors)
                .build();
    }

    /**
     * Handle validation exceptions map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value= ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(constraintViolation -> {
            errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        });
        return errors;
    }

    /**
     * Handle persistencia response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(value = MissingParamsException.class)
    protected ResponseEntity<Object> handlePersistencia(MissingParamsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    /**
     * Handle persistencia response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ContactAlreadyExistsException.class)
    protected ResponseEntity<Object> handlePersistencia(ContactAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handlePersistencia(NotFoundException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessageDTO(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}
