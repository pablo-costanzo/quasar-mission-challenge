package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.impl.SatelliteRepositoryConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories.SatelliteRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SatelliteServiceRepositoryImpl implements SatelliteRepositoryService {

  private final SatelliteRepository satelliteRepository;

  private final SatelliteRepositoryConverter satelliteRepositoryConverter;



  @Override
  public Collection<Positionable> getAll() {
    return satelliteRepository.findAll().stream().map(satelliteRepositoryConverter::mapToEntity)
        .collect(
            Collectors.toList());

  }


  @Override
  public Optional<Positionable> getByName(String name) {
    return satelliteRepository.findById(name).map(satelliteRepositoryConverter::mapToEntity);
  }
}
