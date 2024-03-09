package com.costanzopa.meli.quasarmissionchallenge.core.model.types;

public enum SatelliteNameType {
  KENOBI("kenobi"),
  SKYWALKER("skywalker"),
  SATO("sato");

  private final String text;

  SatelliteNameType(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
