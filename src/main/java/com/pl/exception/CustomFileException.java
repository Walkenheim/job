package com.pl.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomFileException extends RuntimeException {

    String errorMessage;

    public CustomFileException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public CustomFileException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public CustomFileException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public CustomFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessage = errorMessage;
    }
}
