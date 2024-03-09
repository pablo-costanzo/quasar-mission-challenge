package com.costanzopa.meli.quasarmissionchallenge.core.application.ports;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import java.util.Collection;
import java.util.Optional;

public interface SatelliteRepositoryService {

  Collection<Positionable> getAll();

  Optional<Positionable> getByName(String name);
}
