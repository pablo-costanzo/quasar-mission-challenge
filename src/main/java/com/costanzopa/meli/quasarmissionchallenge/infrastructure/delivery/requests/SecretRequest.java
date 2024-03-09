package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretRequest {
  private List<SatelliteInfoRest> satellites;
}
