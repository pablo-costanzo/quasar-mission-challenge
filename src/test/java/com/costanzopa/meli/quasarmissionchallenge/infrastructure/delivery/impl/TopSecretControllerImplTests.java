package com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetMessageDecodeUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.application.usecases.GetSourceLocationUseCase;
import com.costanzopa.meli.quasarmissionchallenge.core.model.entities.impl.XYPosition;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.LocationException;
import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.constants.RestConstants;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SecretRequest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.PositionRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.SecretResponse;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.persistence.repositories.SatelliteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.costanzopa.meli.quasarmissionchallenge.QuasarMissionChallengeApplication.class})
@AutoConfigureMockMvc
public class TopSecretControllerImplTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private GetMessageDecodeUseCase getMessageDecodeUseCase;

  @MockBean
  private GetSourceLocationUseCase getSourceLocationUseCase;

  @MockBean
  SatelliteRepository satelliteRepository;


  @Test
  public void givenCorrectSatelliteInfoShouldDecodeAndLocalizeSourceTest() throws Exception {
    //arrange
    SecretRequest topSecretRequest = new SecretRequest();
    List<SatelliteInfoRest> satelliteInfoRequests = Arrays.asList(
        new SatelliteInfoRest("kenobi", 123, Collections.emptyList()),
        new SatelliteInfoRest("kenobi", 123, Collections.emptyList()),
        new SatelliteInfoRest("kenobi", 123, Collections.emptyList()));
    topSecretRequest.setSatellites(satelliteInfoRequests);
    SecretResponse topSecretResponse = new SecretResponse(new PositionRest(55, -77),
        "este mensaje es secreto");

    Mockito.when(getMessageDecodeUseCase.decode(anyList()))
        .thenReturn("este mensaje es secreto");
    Mockito.when(getSourceLocationUseCase.getSourceLocation(anyList()))
        .thenReturn(new XYPosition(55, -77));
    //act
    MvcResult result = mockMvc.perform(
            MockMvcRequestBuilders.post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                    + RestConstants.RESOURCE_TOP_SECRET + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topSecretRequest)))
        .andExpect(status().isOk()).andReturn();

    //assert
    String content = result.getResponse().getContentAsString();
    assertThat(content).isEqualTo(objectMapper.writeValueAsString(topSecretResponse));

  }


  @Test
  public void givenIncorrectSatelliteInfoShouldThrownALocationExceptionTest() throws Exception {

    SecretRequest topSecretRequest = new SecretRequest();
    List<SatelliteInfoRest> satelliteInfoRequests = List.of(
        new SatelliteInfoRest("kenobi", 123, Collections.emptyList()));
    topSecretRequest.setSatellites(satelliteInfoRequests);

    Mockito.when(getSourceLocationUseCase.getSourceLocation(anyList()))
        .thenThrow(new LocationException("Location Invalid"));

    mockMvc.perform(
            MockMvcRequestBuilders.post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                    + RestConstants.RESOURCE_TOP_SECRET + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topSecretRequest)))
        .andExpect(status().isNotFound());
  }

  @Test
  public void givenIncorrectSatelliteInfoShouldThrownAMessageExceptionTest() throws Exception {

    SecretRequest topSecretRequest = new SecretRequest();
    List<SatelliteInfoRest> satelliteInfoRequests = List.of(
        new SatelliteInfoRest("kenobi", 123, Collections.emptyList()));
    topSecretRequest.setSatellites(satelliteInfoRequests);

    Mockito.when(getSourceLocationUseCase.getSourceLocation(anyList()))
        .thenReturn(new XYPosition(55, -77));
    Mockito.when(getMessageDecodeUseCase.decode(anyList()))
        .thenThrow(new MessageException("Message Invalid"));

    mockMvc.perform(
            MockMvcRequestBuilders.post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                    + RestConstants.RESOURCE_TOP_SECRET + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(topSecretRequest)))
        .andExpect(status().isNotFound());
  }

}
