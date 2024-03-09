package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteInfoRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.impl.SatelliteInfoRepositoryConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.SatelliteInfoEntity;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories.SatelliteInfoRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SatelliteInfoServiceRepositoryImpl implements SatelliteInfoRepositoryService {

  private final SatelliteInfoRepository satelliteInfoRepository;
  private final SatelliteInfoRepositoryConverter satelliteInfoRepositoryConverter;

  @Override
  public void save(Reportable satelliteInfo) {
    SatelliteInfoEntity satelliteInfoEntity = satelliteInfoRepositoryConverter.mapToTable(
        satelliteInfo);
    Optional<SatelliteInfoEntity> toUpdate = satelliteInfoRepository.findById(
        satelliteInfo.getName());
    toUpdate.ifPresent(satelliteInfoRepository::delete);

    satelliteInfoRepository.save(satelliteInfoEntity);

  }

  @Override
  public Collection<Reportable> getAll() {
    return satelliteInfoRepository.findAll().stream()
        .map(satelliteInfoRepositoryConverter::mapToEntity)
        .collect(
            Collectors.toList());
  }
}
