package com.ironhack.team5crm.services.exceptions;

import com.ironhack.team5crm.data.exceptions.DataException;

public class DataNotFoundException extends DataException {

    public DataNotFoundException() {}

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
