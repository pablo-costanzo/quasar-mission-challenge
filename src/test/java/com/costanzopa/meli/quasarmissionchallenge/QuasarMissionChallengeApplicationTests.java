package com.costanzopa.meli.quasarmissionchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.costanzopa.meli.quasarmissionchallenge.core.model.types.SatelliteNameType;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.constants.RestConstants;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SatelliteInfoSplitRest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.requests.SecretRequest;
import com.costanzopa.meli.quasarmissionchallenge.infrastructure.delivery.responses.SecretResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuasarMissionChallengeApplicationTests {

  private static SecretRequest topSecretRequest;

  private static SatelliteInfoSplitRest kenobi;
  private static SatelliteInfoSplitRest skywalker;
  private static SatelliteInfoSplitRest sato;

  @Autowired
  TestRestTemplate testRestTemplate;

  @LocalServerPort
  int randomServerPort;

  @Value("${local.server.url}")
  String localHost;


  @BeforeAll
  static void setUp() {
    topSecretRequest = new SecretRequest();
    kenobi = new SatelliteInfoSplitRest(100, Arrays.asList("", "este", "", "", "mensaje"));
    skywalker = new SatelliteInfoSplitRest(115.5, Arrays.asList("este", "", "un", "mensaje"));
    sato = new SatelliteInfoSplitRest(142.7, Arrays.asList("", "", "es", "", "mensaje"));
    List<SatelliteInfoRest> satelliteInfoRequests = Arrays.asList(
        new SatelliteInfoRest(
            SatelliteNameType.KENOBI.toString(), 100, Arrays.asList("este", "", "", "mensaje", "")),
        new SatelliteInfoRest(SatelliteNameType.SKYWALKER.toString(), 115.5,
            Arrays.asList("", "es", "", "", "secreto")),
        new SatelliteInfoRest(SatelliteNameType.SATO.toString(), 142.7,
            Arrays.asList("este", "", "un", "", "")));
    topSecretRequest.setSatellites(satelliteInfoRequests);

  }

  @Test
  public void postSecret_OK_ReturnLocationAndDecodeMessageTest() {

    //arrange
    HttpEntity<SecretRequest> request = new HttpEntity<>(topSecretRequest);

    //act
    ResponseEntity<SecretResponse> response = testRestTemplate.postForEntity(
        localHost + randomServerPort + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
            + RestConstants.RESOURCE_TOP_SECRET + "/", request, SecretResponse.class);

    //assert
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(Objects.requireNonNull(response.getBody()).getMessage(),
        "este es un mensaje secreto");

    double acceptedDelta = 0.01;
    assertEquals(Objects.requireNonNull(response.getBody()).getPosition().getX(), 53.02,
        acceptedDelta);
    assertEquals(Objects.requireNonNull(response.getBody()).getPosition().getY(), -33.10,
        acceptedDelta);
  }


  @Test
  public void postSplitSecret_OK_ReturnSatelliteInfoAdded() {
    //arrange
    HttpEntity<SatelliteInfoSplitRest> request = new HttpEntity<>(kenobi);

    //act
    ResponseEntity<SatelliteInfoRest> response = testRestTemplate.postForEntity(
        localHost + randomServerPort + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
            + RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/kenobi", request,
        SatelliteInfoRest.class);

    //assert
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(Objects.requireNonNull(response.getBody()).getName(),
        "kenobi");
  }


  @Test
  public void getSplitSecret_FAIL_ReturnNotEnoughInformation() {
    //arrange
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add("Content-Type", "application/json");
    //act
    ResponseEntity<String> response = testRestTemplate.exchange(
        localHost + randomServerPort + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
            + RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/", HttpMethod.GET,
        new HttpEntity<>(headers), String.class);

    //assert
    assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    assertEquals(Objects.requireNonNull(response.getBody()), "No hay suficiente información.");
  }


  @Test
  public void getSplitSecret_OK_ReturnLocationAndDecodeMessageTest() {
    //arrange
    HttpEntity<SatelliteInfoSplitRest> kenobiRequest = new HttpEntity<>(kenobi);
    HttpEntity<SatelliteInfoSplitRest> satoRequest = new HttpEntity<>(sato);
    HttpEntity<SatelliteInfoSplitRest> skywalkerRequest = new HttpEntity<>(skywalker);

    testRestTemplate.postForEntity(
        localHost + randomServerPort + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
            + RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/kenobi", kenobiRequest,
        SatelliteInfoRest.class);

    testRestTemplate.postForEntity(
        localHost + randomServerPort + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
            + RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/sato", satoRequest,
        SatelliteInfoRest.class);

    testRestTemplate.postForEntity(
        localHost + randomServerPort + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
            + RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/skywalker", skywalkerRequest,
        SatelliteInfoRest.class);

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add("Content-Type", "application/json");

    //act
    ResponseEntity<SecretResponse> response = testRestTemplate.exchange(
        localHost + randomServerPort + RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
            + RestConstants.RESOURCE_TOP_SECRET_SPLIT + "/", HttpMethod.GET,
        new HttpEntity<>(headers), SecretResponse.class);

    //assert
    assertEquals(response.getStatusCode(), HttpStatus.OK);
    assertEquals(Objects.requireNonNull(response.getBody()).getMessage(),
        "este es un mensaje");

    double acceptedDelta = 0.01;
    assertEquals(Objects.requireNonNull(response.getBody()).getPosition().getX(), 53.02,
        acceptedDelta);
    assertEquals(Objects.requireNonNull(response.getBody()).getPosition().getY(), -33.10,
        acceptedDelta);
  }
}
