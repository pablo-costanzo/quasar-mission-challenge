package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PositionRest implements Serializable {

  private double x;
  private double y;

  public double getX() {
    return round(this.x, 2);
  }

  public double getY() {
    return round(this.y, 2);
  }

  public double round(double value, int places) {
    if (places < 0) {
      throw new IllegalArgumentException();
    }

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }
}
