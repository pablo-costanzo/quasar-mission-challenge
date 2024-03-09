package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.XYPosition;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Positionable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.LocationException;
import com.costanzopa.meli.quasarmissionchallenge.core.model.location.LocatableMethod;
import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetSourceLocationUseCase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetSourceLocationUseCaseImpl implements GetSourceLocationUseCase {

  private LocatableMethod locatable;
  private SatelliteRepositoryService satelliteRepositoryService;
  private final static int MIN_POSITIONS = 3;
  private final static int MIN_DISTANCES = 3;
  private final static String NOT_ENOUGH_INFORMATION = "No hay suficiente información.";


  @Override
  public XYPosition getSourceLocation(List<Reportable> satelliteInfoList)
      throws LocationException {

    Collection<Positionable> positionables = new ArrayList<>();

    if (satelliteInfoList.size() < MIN_DISTANCES) {
      throw new LocationException(NOT_ENOUGH_INFORMATION);
    }

    satelliteInfoList.forEach(satelliteInfo -> {
      String name = satelliteInfo.getName();
      Optional<Positionable> positionable = satelliteRepositoryService.getByName(name);
      positionable.ifPresent(positionables::add);
    });

    if (positionables.size() < MIN_POSITIONS) {
      throw new LocationException(NOT_ENOUGH_INFORMATION);
    }

    double[][] positions = positionables.stream().map(Positionable::getPosition)
        .toArray(double[][]::new);

    double[] distances = satelliteInfoList.stream().mapToDouble(Reportable::getDistance)
        .toArray();

    double[] location;
    try {
      location = locatable.getLocation(distances, positions);
    } catch (Exception e) {
      throw new LocationException(e.getMessage());
    }

    return new XYPosition(location);
  }
}
