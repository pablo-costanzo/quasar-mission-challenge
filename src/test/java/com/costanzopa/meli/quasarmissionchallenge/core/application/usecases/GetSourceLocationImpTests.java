package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.costanzopa.meli.quasarmissionchallenge.core.application.ports.SatelliteRepositoryService;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl.GetSourceLocationUseCaseImpl;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.Satellite;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.builders.SatelliteBuilder;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.builders.SatelliteInfoBuilder;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.LocationException;
import com.costanzopa.meli.quasarmissionchallenge.core.model.location.LocatableMethod;
import com.costanzopa.meli.quasarmissionchallenge.core.model.types.SatelliteNameType;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetSourceLocationImpTests {

  private final static String NOT_ENOUGH_INFORMATION = "No hay suficiente información.";

  @Mock
  private LocatableMethod locatableMethod;

  @Mock
  private SatelliteRepositoryService satelliteRepositoryService;

  @InjectMocks
  private GetSourceLocationUseCase getSourceLocation = new GetSourceLocationUseCaseImpl(
      locatableMethod,
      satelliteRepositoryService);


  @Test
  public void setGetSourceLocationValidTest() throws LocationException {
    //Arrange
    Satellite skywalker = new SatelliteBuilder().name(SatelliteNameType.KENOBI.toString()).position(-500.0, -200.0).build();

    Reportable satelliteInfoSkyWalker = new SatelliteInfoBuilder().name(SatelliteNameType.SATO.toString())
        .distance(100).build();
    Reportable satelliteInfoKenoby = new SatelliteInfoBuilder().name(SatelliteNameType.KENOBI.toString()).distance(115.5)
        .build();
    Reportable satelliteInfoSato = new SatelliteInfoBuilder().name(SatelliteNameType.SATO.toString()).distance(142.7)
        .build();

    double[] expectedPosition = new double[]{-58.31, -69.55};
    double acceptedDelta = 0.01;

    Mockito.when(satelliteRepositoryService.getByName(anyString()))
        .thenReturn(java.util.Optional.ofNullable(skywalker));
    Mockito.when(locatableMethod.getLocation(any(double[].class), any(double[][].class)))
        .thenReturn(expectedPosition);

    //Act
    double[] sourcePosition = getSourceLocation.getSourceLocation(
            Arrays.asList(satelliteInfoSkyWalker, satelliteInfoKenoby, satelliteInfoSato))
        .getPosition();

    //Assert
    assertArrayEquals(sourcePosition, expectedPosition, acceptedDelta);
  }


  @Test
  public void whenYouDontHaveEnoughDistancesToCalculateThrewAnExceptionTest() {
    LocationException thrown = assertThrows(
        LocationException.class,
        () -> {
          //Act
          getSourceLocation.getSourceLocation(
              Collections.emptyList());
        }
    );
    //Assert
    assertTrue(thrown.getMessage().contains(NOT_ENOUGH_INFORMATION));
  }


  @Test
  public void whenYouDontHaveEnoughSatellitesToCalculateThrewAnExceptionTest() {
    LocationException thrown = assertThrows(
        LocationException.class,
        () -> {
          //Arrange
          Reportable satelliteInfoSkyWalker = new SatelliteInfoBuilder().name(
                  SatelliteNameType.SKYWALKER.toString())
              .distance(100).build();
          Reportable satelliteInfoKenoby = new SatelliteInfoBuilder().name(
                  SatelliteNameType.KENOBI.toString())
              .distance(115.5)
              .build();
          Reportable satelliteInfoSato = new SatelliteInfoBuilder().name(
                  SatelliteNameType.SATO.toString()).distance(142.7)
              .build();
          Mockito.when(satelliteRepositoryService.getByName(anyString()))
              .thenReturn(Optional.empty());
          //Act
          getSourceLocation.getSourceLocation(
              Arrays.asList(satelliteInfoSkyWalker, satelliteInfoKenoby, satelliteInfoSato));
        }
    );
    //Assert
    assertTrue(thrown.getMessage().contains(NOT_ENOUGH_INFORMATION));
  }

}
