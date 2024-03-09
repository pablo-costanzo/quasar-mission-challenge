package com.costanzopa.meli.quasarmissionchallenge.core.model.location;

public interface LocatableMethod {
  double[] getLocation(double[] distances, double[][] positions);
}
