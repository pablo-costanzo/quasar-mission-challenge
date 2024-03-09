package com.costanzopa.meli.quasarmissionchallenge.core.model.entities.builders;


import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.SatelliteInfo;
import java.util.Collections;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SatelliteInfoBuilder {

  private String name = "";

  private double distance = 0.0;

  private List<String> message = Collections.emptyList();


  public SatelliteInfoBuilder name(String name) {
    this.name = name;
    return this;
  }

  public SatelliteInfoBuilder distance(double distance) {
    this.distance = distance;
    return this;
  }

  public SatelliteInfoBuilder message(List<String> message) {
    this.message = message;
    return this;
  }

  public SatelliteInfo build() {
    return new SatelliteInfo(this.name, this.distance, this.message);
  }
}
