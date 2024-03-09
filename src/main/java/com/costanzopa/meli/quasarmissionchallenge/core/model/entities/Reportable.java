package com.costanzopa.meli.quasarmissionchallenge.core.model.entities;

import java.io.Serializable;
import java.util.List;

public interface Reportable extends Serializable {

  String getName();
  double getDistance();
  List<String> getMessage();
}
