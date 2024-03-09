package com.costanzopa.meli.quasarmissionchallenge.core.model.location;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Arrays;
import java.util.PrimitiveIterator.OfDouble;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TrilaterationLocationMethodTests {

  private static LocatableMethod locatable;

  @BeforeAll
  static void setUp() {
    locatable = new TrilaterationLocationMethodImp();
  }

  @Test
  public void trilateration2DExact3DummyTest() {
    double[][] positions = new double[][]{{0.0, 0.0}, {1000.0, 0.0}, {0.0, 1000.0}};
    double[] distances = new double[]{Math.sqrt(2.0) * 1000.0, 1000.0, 1000.0};
    double[] expectedPosition = new double[]{1000.0, 1000.0};
    double acceptedDelta = 0.0001;
    double[] location = locatable.getLocation(distances, positions);
    assertArrayEquals(expectedPosition, location, acceptedDelta);
  }

  @Test
  public void trilateration2DExact3DecimalComplexTest() {
    double[][] positions = new double[][]{{832.165, 5148.059}, {741.264, 5242.310},
        {863.763, 5245.127}};
    double[] distances = new double[]{86.814, 69.409, 55.448};
    double[] expectedPosition = new double[]{809.898, 5231.968};
    double acceptedDelta = 0.001;
    double[] location = locatable.getLocation(distances, positions);
    assertArrayEquals(expectedPosition, location, acceptedDelta);
  }

  @Test
  public void trilateration2DFailTest() {
    double[][] positions = new double[][]{{-500, 200}, {100, -100}, {500, 100}};
    double[] distances = new double[]{100, 115.5, 142.7};
    double[] expectedPosition = new double[]{-100, 75.5};
    double[] location = locatable.getLocation(distances, positions);
    OfDouble iteratorLocation = Arrays.stream(location).iterator();
    OfDouble iteratorExpectedPosition = Arrays.stream(expectedPosition).iterator();

    assertEquals(location.length, expectedPosition.length);
    while (iteratorLocation.hasNext() && iteratorExpectedPosition.hasNext()) {
      assertNotEquals(iteratorLocation.next(), iteratorExpectedPosition.next());
    }
  }

  @Test
  public void whenYouDontHaveEnoughPositionsToCalculateThrewAnExceptionTest() {
    IllegalArgumentException thrown = assertThrows(
        IllegalArgumentException.class,
        () -> {
          double[][] positions = new double[][]{{-500, 200}, {100, -100}};
          double[] distances = new double[]{100, 115.5, 142.7};
          double[] location = locatable.getLocation(distances, positions);
        }
    );
    assertTrue(thrown.getMessage().contains(
        "The number of positions you provided, 2, does not match the number of distances, 3."));
  }

}
