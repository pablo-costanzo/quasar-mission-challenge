package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.builder;

import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.SatelliteEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SatelliteEntityBuilder {

  private String name = "";
  private double x = 0;
  private double y = 0;

  public SatelliteEntityBuilder name(String name) {
    this.name = name;
    return this;
  }

  public SatelliteEntityBuilder x(double x) {
    this.x = x;
    return this;
  }

  public SatelliteEntityBuilder y(double y) {
    this.y = y;
    return this;
  }

  public SatelliteEntity build() {
    return new SatelliteEntity(this.name, this.x, this.y);
  }
}
