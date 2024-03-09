package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases;

import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import java.util.List;

public interface GetMessageDecodeUseCase {

  String decode(List<Reportable> satelliteInfos) throws MessageException;
}
