package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.impl;


import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.SatelliteInfo;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.RepositoryConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.SatelliteInfoEntity;

public class SatelliteInfoRepositoryConverter implements
    RepositoryConverter<Reportable, SatelliteInfoEntity> {

  public SatelliteInfoEntity mapToTable(Reportable satelliteInfo) {
    return new SatelliteInfoEntity(satelliteInfo.getName(), satelliteInfo.getDistance(),
        satelliteInfo.getMessage());
  }

  public SatelliteInfo mapToEntity(SatelliteInfoEntity satelliteEntity) {
    return new SatelliteInfo(satelliteEntity.getName(), satelliteEntity.getDistance(),
        satelliteEntity.getMessage());
  }
}
