package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery;


import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.SatelliteRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Satellite API")
public interface SatelliteController {
  @Operation(
      summary = "Obtener todos los Satelites activos.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Respuesta Exitosa", content = @Content)})
  ResponseEntity<List<SatelliteRest>> getAll();
}
