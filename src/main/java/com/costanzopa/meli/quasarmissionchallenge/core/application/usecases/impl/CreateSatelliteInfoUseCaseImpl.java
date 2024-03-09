package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteInfoRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.CreateSatelliteInfoUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.SatelliteException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CreateSatelliteInfoUseCaseImpl implements CreateSatelliteInfoUseCase {

  private SatelliteRepositoryService satelliteRepositoryService;
  private SatelliteInfoRepositoryService satelliteInfoRepositoryService;

  @Override
  public void execute(Reportable satelliteInfo) {
    String satelliteName = satelliteInfo.getName();

    Optional<Positionable> currentSatellite = satelliteRepositoryService.getByName(satelliteName);
    if (currentSatellite.isEmpty()) {
      throw new SatelliteException("Satellite with name: %s not Found.", satelliteName);
    }

    satelliteInfoRepositoryService.save(satelliteInfo);
  }
}
