package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.Satellite;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.builders.SatelliteBuilder;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.RestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl.PositionRestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.PositionRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.SatelliteRest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SatelliteRestConverter implements RestConverter<SatelliteRest, Positionable> {

  private PositionRestConverter positionRestConverter;

  @Override
  public Satellite mapToEntity(final SatelliteRest rest) {

    return new SatelliteBuilder().name(rest.getName())
        .position(positionRestConverter.mapToEntity(rest.getPosition()).getX(),
            positionRestConverter.mapToEntity(
                rest.getPosition()).getY()).build();
  }

  @Override
  public SatelliteRest mapToRest(final Positionable entity) {
    return new SatelliteRest(entity.getName(),
        new PositionRest(entity.getX(), entity.getY()));
  }

}
