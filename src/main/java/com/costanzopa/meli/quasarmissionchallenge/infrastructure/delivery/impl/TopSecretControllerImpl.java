package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.impl;


import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.CreateSatelliteInfoUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetAllSatelliteInfoUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetMessageDecodeUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetSourceLocationUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.XYPosition;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.SatelliteInfo;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.LocationException;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.SatelliteException;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.constants.RestConstants;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.TopSecretController;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl.PositionRestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.converters.impl.SatelliteInfoRestConverter;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SecretRequest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoSplitRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.SecretResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1)
@RequiredArgsConstructor
public class TopSecretControllerImpl implements TopSecretController {

  private final GetSourceLocationUseCase getSourceLocationUseCase;
  private final GetMessageDecodeUseCase getMessageDecodeUseCase;
  private final CreateSatelliteInfoUseCase createSatelliteInfoUseCase;
  private final GetAllSatelliteInfoUseCase getAllSatelliteInfoUseCase;
  private final SatelliteInfoRestConverter satelliteInfoRestConverter;
  private final PositionRestConverter positionRestConverter;

  @Override
  @PostMapping(path = RestConstants.RESOURCE_TOP_SECRET,
      consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<SecretResponse> postTopSecret(
      @RequestBody SecretRequest secretRequest) throws LocationException, MessageException {

    List<SatelliteInfoRest> satellites = secretRequest.getSatellites();
    List<Reportable> satelliteInfos = satellites.stream()
        .map(satelliteInfoRestConverter::mapToEntity)
        .collect(
            Collectors.toList());

    XYPosition sourceLocationXYPosition = getSourceLocationUseCase.getSourceLocation(
        satelliteInfos);
    String decodeMessage = getMessageDecodeUseCase.decode(satelliteInfos);
    SecretResponse secretResponse = new SecretResponse(
        positionRestConverter.mapToRest(sourceLocationXYPosition),
        decodeMessage);
    return ResponseEntity.status(HttpStatus.OK).body(secretResponse);
  }

  @Override
  @PostMapping(path = RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/{satellite_name}",
      consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<SatelliteInfoRest> postTopSecretSplit(
      @PathVariable(name = "satellite_name") String satelliteName,
      @RequestBody SatelliteInfoSplitRest satelliteInfoSplitRest) throws SatelliteException {
    SatelliteInfoRest satelliteInfoRest = new SatelliteInfoRest(satelliteName,
        satelliteInfoSplitRest.getDistance(), satelliteInfoSplitRest.getMessage());
    SatelliteInfo satelliteInfo = satelliteInfoRestConverter.mapToEntity(satelliteInfoRest);
    createSatelliteInfoUseCase.execute(satelliteInfo);
    return ResponseEntity.status(HttpStatus.OK).body(satelliteInfoRest);
  }

  @Override
  @GetMapping(path = RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/", produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<SecretResponse> getTopSecretSplit()
      throws LocationException, MessageException {

    List<Reportable> satelliteInfos = new ArrayList<>(
        getAllSatelliteInfoUseCase.execute());
    XYPosition sourceLocationXYPosition = getSourceLocationUseCase.getSourceLocation(
        satelliteInfos);
    String decodeMessage = getMessageDecodeUseCase.decode(satelliteInfos);

    SecretResponse secretResponse = new SecretResponse(
        positionRestConverter.mapToRest(sourceLocationXYPosition),
        decodeMessage);

    return ResponseEntity.status(HttpStatus.OK).body(secretResponse);
  }

}
