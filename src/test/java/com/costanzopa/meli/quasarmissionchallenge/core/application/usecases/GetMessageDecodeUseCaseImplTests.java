package com.costanzopa.meli.quasarmissionchallenge.core.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.impl.GetMessageDecodeUseCaseImpl;
import com.costanzopa.meli.quasarmissionchallenge.core.model.decode.DecodeMethod;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.SatelliteInfo;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.builders.SatelliteInfoBuilder;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetMessageDecodeUseCaseImplTests {

  @Mock
  DecodeMethod decodeMethod;

  @InjectMocks
  GetMessageDecodeUseCase getMessageDecodeUseCase = new GetMessageDecodeUseCaseImpl(decodeMethod);


  @Test
  public void decodeMessageValidTest() throws MessageException {
    SatelliteInfo satelliteInfoSkyWalker = new SatelliteInfoBuilder()
        .message(Arrays.asList("este", "", "", "mensaje", "")).build();
    SatelliteInfo satelliteInfoKenoby = new SatelliteInfoBuilder()
        .message(Arrays.asList("", "es", "", "", "secreto"))
        .build();
    SatelliteInfo satelliteInfoSato = new SatelliteInfoBuilder()
        .message(Arrays.asList("este", "", "un", "", ""))
        .build();

    Mockito.when(decodeMethod.decode(any(String[][].class)))
        .thenReturn("este es un mensaje secreto");
    String decode = getMessageDecodeUseCase.decode(
        Arrays.asList(satelliteInfoKenoby, satelliteInfoSato, satelliteInfoSkyWalker));
    assertEquals("este es un mensaje secreto", decode);
  }

}
