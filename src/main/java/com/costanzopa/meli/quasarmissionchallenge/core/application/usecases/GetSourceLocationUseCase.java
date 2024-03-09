package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.XYPosition;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.LocationException;
import java.util.List;

public interface GetSourceLocationUseCase {

  XYPosition getSourceLocation(List<Reportable> satelliteInfoList) throws LocationException;
}
