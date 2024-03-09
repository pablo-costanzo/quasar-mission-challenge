package com.costanzopa.meli.quasarmissionchallenge.core.model.entities.builders;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.XYPosition;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.Satellite;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SatelliteBuilder {

  private String name = "";
  private XYPosition XYPosition = new XYPosition(0, 0);

  public SatelliteBuilder name(String name) {
    this.name = name;
    return this;
  }

  public SatelliteBuilder position(double x, double y) {
    this.XYPosition = new XYPosition(x, y);
    return this;
  }


  public Satellite build() {
    return new Satellite(this.name, this.XYPosition);
  }
}
