package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery;

import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.LocationException;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SecretRequest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoSplitRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.SecretResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Top Secret API")
public interface TopSecretController {

  @Operation(
      summary = "Obtener la ubicación de la nave y el mensaje que emite.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Respuesta Exitosa", content = @Content),
      @ApiResponse(responseCode = "404", description = "No se puede determinar la posición o el mensaje")})
  ResponseEntity<SecretResponse> postTopSecret(SecretRequest secretRequest)
      throws LocationException, MessageException;


  @Operation(
      summary = "Guardar mesaje llegado al satelite")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Respuesta Exitosa", content = @Content),
      @ApiResponse(responseCode = "404", description = "No se puede determinar la posición o el mensaje.")})
  ResponseEntity<SatelliteInfoRest> postTopSecretSplit(String satelliteName,
      SatelliteInfoSplitRest secretSplitRequest);

  @Operation(
      summary = "Obtener la ubicación de la nave y el mensaje que emite.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Respuesta Exitosa", content = @Content),
      @ApiResponse(responseCode = "404", description = "No hay suficiente información para determinar la posicion o el mensaje.")})
  ResponseEntity<SecretResponse> getTopSecretSplit() throws LocationException, MessageException;
}
