package com.ironhack.team5crm.data.exceptions;

import com.ironhack.team5crm.domain.exceptions.Team5CrmException;

public class DataException extends Team5CrmException {

    public DataException(){}

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }

    public DataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
