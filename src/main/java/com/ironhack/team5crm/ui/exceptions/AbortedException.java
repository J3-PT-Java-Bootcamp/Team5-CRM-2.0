package com.ironhack.team5crm.ui.exceptions;

public class AbortedException extends UiException {

  public AbortedException() {
  }

  public AbortedException(String message) {
    super(message);
  }

  public AbortedException(String message, Throwable cause) {
    super(message, cause);
  }

  public AbortedException(Throwable cause) {
    super(cause);
  }

  public AbortedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
