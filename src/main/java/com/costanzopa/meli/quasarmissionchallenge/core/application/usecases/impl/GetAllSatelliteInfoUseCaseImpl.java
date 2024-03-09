package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteInfoRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetAllSatelliteInfoUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class GetAllSatelliteInfoUseCaseImpl implements GetAllSatelliteInfoUseCase {
  private SatelliteInfoRepositoryService satelliteInfoRepositoryService;
  @Override
  public Collection<Reportable> execute() {
    return satelliteInfoRepositoryService.getAll();
  }
}
