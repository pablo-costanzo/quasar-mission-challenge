package com.costanzopa.meli.quasarmissionchallenge.core.model.location;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

public class TrilaterationLocationMethodImp implements LocatableMethod {

  @Override
  public double[] getLocation(double[] distances, double[][] positions) {
    NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
        new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());

    LeastSquaresOptimizer.Optimum optimum = solver.solve();

    return optimum.getPoint().toArray();
  }
}
