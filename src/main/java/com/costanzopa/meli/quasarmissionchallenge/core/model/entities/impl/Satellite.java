package com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Satellite implements Positionable {

  private String name;
  private XYPosition position;

  public double[] getPosition() {
    return new double[]{position.getX(), position.getY()};
  }

  @Override
  public double getX() {
    return position.getX();
  }

  @Override
  public double getY() {
    return position.getY();
  }
}
