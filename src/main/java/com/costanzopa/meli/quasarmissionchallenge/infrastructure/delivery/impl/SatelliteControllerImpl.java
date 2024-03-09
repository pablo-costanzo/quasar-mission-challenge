package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetAllSatellitesUseCase;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.constants.RestConstants;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.SatelliteController;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl.SatelliteRestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.SatelliteRest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
    + RestConstants.RESOURCE_SATELLITE)
public class SatelliteControllerImpl implements SatelliteController {

  private GetAllSatellitesUseCase getAllSatellitesUseCase;
  private SatelliteRestConverter satelliteRestConverter;

  @Override
  @GetMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<SatelliteRest>> getAll() {
    List<SatelliteRest> collect = getAllSatellitesUseCase.execute().stream()
        .map(positionable -> satelliteRestConverter.mapToRest(positionable)).collect(
            Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(collect);
  }
}
