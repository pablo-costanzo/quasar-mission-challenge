package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetAllSatellitesUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import java.util.Collection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAllSatellitesUseCaseImpl implements GetAllSatellitesUseCase {

  private SatelliteRepositoryService satelliteRepositoryService;

  @Override
  public Collection<Positionable> execute() {
    return satelliteRepositoryService.getAll();
  }
}
