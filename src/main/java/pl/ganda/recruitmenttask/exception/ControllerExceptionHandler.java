package pl.ganda.recruitmenttask.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NoResultException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final HttpHeaders header;

    public ControllerExceptionHandler() {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        this.header = header;
    }

    @ExceptionHandler(value = { NullPointerException.class})
    protected ResponseEntity<Object> handleInternalException(Exception ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR), header, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler(value = { NoResultException.class})
    protected ResponseEntity<Object> handleNoResultExceptionException(Exception ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), header, HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value = { NoRecordException.class})
    protected ResponseEntity<Object> handleNoRecordException(Exception ex, WebRequest webRequest) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), header, HttpStatus.BAD_REQUEST, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()), header, HttpStatus.BAD_REQUEST, request);
    }
}
