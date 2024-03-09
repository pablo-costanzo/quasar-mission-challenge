package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import java.util.Collection;

public interface GetAllSatelliteInfoUseCase {

  Collection<Reportable> execute();
}
