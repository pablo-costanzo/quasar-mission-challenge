package com.costanzopa.meli.quasarmissionchallenge.core.application.ports;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import java.util.Collection;


public interface SatelliteInfoRepositoryService {

  void save(Reportable satelliteInfo);

  Collection<Reportable> getAll();
}
