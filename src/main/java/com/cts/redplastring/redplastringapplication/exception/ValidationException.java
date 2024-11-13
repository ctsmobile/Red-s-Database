package com.cts.redplastring.redplastringapplication.exception;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Locale;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValidationException extends RuntimeException{

    private static final long serialVersionUID = -2222027115430373997L;
    private String message;
    private String frontendMessage;
    private Locale locale;

    public ValidationException() {
        super();
    }

    public ValidationException(String message,String frontendmessage) {
        super(message);
        this.message=message;
        this.frontendMessage=frontendmessage;
    }

    public ValidationException(String message,String frontendmessage,Locale locale) {
        super(message);
        this.message=message;
        this.frontendMessage=frontendmessage;
        this.locale=locale;
    }

}
