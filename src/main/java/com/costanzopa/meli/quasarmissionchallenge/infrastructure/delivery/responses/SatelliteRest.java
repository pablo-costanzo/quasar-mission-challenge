package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SatelliteRest implements Serializable {

  private String name;
  private PositionRest position;
}
