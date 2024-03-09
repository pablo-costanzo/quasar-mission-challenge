package com.costanzopa.meli.quasarmissionchallenge.core.model.entities;

import java.io.Serializable;

public interface Positionable extends Serializable {

  double[] getPosition();

  String getName();

  double getX();

  double getY();
}
