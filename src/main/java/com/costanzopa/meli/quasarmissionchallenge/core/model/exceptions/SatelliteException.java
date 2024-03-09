package com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions;

public class SatelliteException extends
    RuntimeException {

  public SatelliteException(String message, String satelliteName) {
    super(String.format(message, satelliteName));
  }
}
