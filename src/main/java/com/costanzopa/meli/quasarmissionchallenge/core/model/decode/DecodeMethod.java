package com.costanzopa.meli.quasarmissionchallenge.core.model.decode;

import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;

public interface DecodeMethod {
  String decode(String[][] messages) throws MessageException;
}
