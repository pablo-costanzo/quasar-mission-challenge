package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import java.util.Collection;

public interface GetAllSatellitesUseCase {
   Collection<Positionable> execute();
}
