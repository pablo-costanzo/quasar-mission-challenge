package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SatelliteInfoRest extends SatelliteInfoSplitRest {

  private String name;


  public SatelliteInfoRest(String name, double distance, List<String> message) {
    super(distance, message);
    this.name = name;
  }

}
