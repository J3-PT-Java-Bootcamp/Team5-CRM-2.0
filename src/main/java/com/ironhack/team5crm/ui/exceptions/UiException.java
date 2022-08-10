package com.ironhack.team5crm.ui.exceptions;

import com.ironhack.team5crm.models.exceptions.Team5CrmException;

public class UiException extends Team5CrmException {

  public UiException() {
  }

  public UiException(String message) {
    super(message);
  }

  public UiException(String message, Throwable cause) {
    super(message, cause);
  }

  public UiException(Throwable cause) {
    super(cause);
  }

  public UiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
