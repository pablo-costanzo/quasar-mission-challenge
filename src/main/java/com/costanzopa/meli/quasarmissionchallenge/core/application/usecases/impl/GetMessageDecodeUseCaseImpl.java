package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl;

import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetMessageDecodeUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.model.decode.DecodeMethod;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.Reportable;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.SatelliteInfo;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetMessageDecodeUseCaseImpl implements GetMessageDecodeUseCase {

  private DecodeMethod decodeMethod;

  @Override
  public String decode(List<Reportable> satelliteInfos) throws MessageException {
    List<List<String>> collect = satelliteInfos.stream().map(Reportable::getMessage)
        .collect(Collectors.toList());
    String[][] messages = collect.stream().map(list -> list.toArray(String[]::new))
        .toArray(String[][]::new);
    return decodeMethod.decode(messages);
  }
}
