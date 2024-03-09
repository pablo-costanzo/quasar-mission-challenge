package com.costanzopa.meli.quasarmissionchallenge.core.model.decode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RebelDecoderMethodTests {

  private static DecodeMethod decodeMethod;

  @BeforeAll
  static void setUp() {
    decodeMethod = new RebelDecoderMethod();
  }


  @Test
  public void decodeMessageWithShiftsShouldReturnOkTest()
      throws MessageException {
    String[][] messages = {{"", "este", "", "", "mensaje"}, {"este", "", "un", "mensaje"},
        {"", "", "es", "", "mensaje"}};

    String secret = decodeMethod.decode(messages);

    assertEquals("este es un mensaje", secret);
  }


  @Test
  public void decodeMessageWithOutShiftsShouldReturnOkTest()
      throws MessageException {
    String[][] messages = {{"este", "", "", "mensaje", ""}, {"", "es", "", "", "secreto"},
        {"este", "", "un", "", ""}};

    String secret = decodeMethod.decode(messages);

    assertEquals("este es un mensaje secreto", secret);
  }

  @Test
  public void decodeMessageWithOutFirstPivotShouldReturnAnExceptionTest() {
    MessageException thrown = assertThrows(
        MessageException.class,
        () -> {
          String[][] messages = {{"", "este", "", "", "mensaje"}, {"", "este", "", "un", "mensaje"},
              {"", "", "es", "", "mensaje"}};
          String secret = decodeMethod.decode(messages);
        }

    );
    assertTrue(thrown.getMessage().contains(
        "It is impossible to decode the message.You need an unshifted one."));
  }


  @Test
  public void decodeMessageWithWrongResultShouldReturnAnExceptionTest() {
    MessageException thrown = assertThrows(
        MessageException.class,
        () -> {
          String[][] messages = {{"este", "", "", "mensaje"}, {"este", "", "un", "mensaje"},
              { "", "", "", "mensaje"}};
          String secret = decodeMethod.decode(messages);
        }

    );
    assertTrue(thrown.getMessage().contains(
        "It is impossible to decode the message."));
  }


  @Test
  public void decodeMessageWithLessSizesShouldReturnAnExceptionTest() {
    MessageException thrown = assertThrows(
        MessageException.class,
        () -> {
          String[][] messages = {{"este", "", "", "mensaje", ""}, {"", "es", "", "secreto"},
              {"este", "", "un", "", ""}};
          String secret = decodeMethod.decode(messages);
        }

    );
    assertTrue(thrown.getMessage().contains(
        "It is impossible to decode the message."));
  }

}
