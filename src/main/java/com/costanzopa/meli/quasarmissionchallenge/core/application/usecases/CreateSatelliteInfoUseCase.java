package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.SatelliteException;

public interface CreateSatelliteInfoUseCase {

  void execute(Reportable satelliteInfo) throws SatelliteException;
}
