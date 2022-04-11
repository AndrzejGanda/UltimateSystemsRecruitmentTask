package pl.ganda.recruitmenttask.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private final int code;
    private final String message;

    public ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorResponse(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
