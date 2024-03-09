package com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.XYPosition;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.Satellite;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.converters.RepositoryConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.entities.SatelliteEntity;

public class SatelliteRepositoryConverter implements
    RepositoryConverter<Positionable,SatelliteEntity> {

  public SatelliteEntity mapToTable(Positionable positionable) {
    return new SatelliteEntity(positionable.getName(), positionable.getPosition());
  }

  public Positionable mapToEntity(SatelliteEntity satelliteEntity) {
    return new Satellite(satelliteEntity.getName(),
        new XYPosition(satelliteEntity.getX(), satelliteEntity.getY()));
  }
}
