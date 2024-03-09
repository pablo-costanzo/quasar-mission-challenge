package com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SatelliteInfo implements Reportable {

  private String name;

  private double distance;

  private List<String> message;
}
