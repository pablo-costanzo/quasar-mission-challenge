package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.XYPosition;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.RestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.PositionRest;

public class PositionRestConverter implements RestConverter<PositionRest, XYPosition> {

  @Override
  public XYPosition mapToEntity(final PositionRest rest) {

    return new XYPosition(rest.getX(), rest.getY());
  }

  @Override
  public PositionRest mapToRest(final XYPosition entity) {
    return new PositionRest(entity.getX(), entity.getY());
  }
}
