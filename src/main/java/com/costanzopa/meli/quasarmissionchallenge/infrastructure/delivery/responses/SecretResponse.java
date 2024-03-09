package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretResponse {
  private PositionRest position;
  private String message;
}
