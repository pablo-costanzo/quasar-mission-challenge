package com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class XYPosition implements Serializable {

  private final static int NUM_DIMENSIONS = 2;

  private double x;
  private double y;

  public XYPosition(double[] coordinates) {
    if (coordinates.length == NUM_DIMENSIONS) {
      x = coordinates[0];
      y = coordinates[1];
    }
  }

  public double[] getPosition() {
    return new double[]{this.getX(), this.getY()};
  }

}
