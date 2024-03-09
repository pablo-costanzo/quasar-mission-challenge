package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.SatelliteInfo;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.RestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoRest;

public class SatelliteInfoRestConverter implements
    RestConverter<SatelliteInfoRest, SatelliteInfo> {

  @Override
  public SatelliteInfo mapToEntity(final SatelliteInfoRest rest) {

    return new SatelliteInfo(rest.getName(), rest.getDistance(), rest.getMessage());
  }

  @Override
  public SatelliteInfoRest mapToRest(final SatelliteInfo entity) {
    return new SatelliteInfoRest(entity.getName(), entity.getDistance(), entity.getMessage());
  }
}
