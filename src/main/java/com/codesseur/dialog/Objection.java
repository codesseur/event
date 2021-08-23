package com.codesseur.dialog;

import static java.util.Objects.requireNonNull;

public class Objection {

  private final RuntimeException exception;

  public Objection(RuntimeException exception) {
    this.exception = requireNonNull(exception);
  }

  RuntimeException exception() {
    return exception;
  }

  public void throwException() {
    throw exception();
  }

}
