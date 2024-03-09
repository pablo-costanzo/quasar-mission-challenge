package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteInfoSplitRest implements Serializable {

  private double distance;

  private List<String> message;

}
