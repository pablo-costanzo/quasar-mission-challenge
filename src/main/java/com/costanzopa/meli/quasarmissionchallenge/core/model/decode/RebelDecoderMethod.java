package com.costanzopa.meli.quasarmissionchallenge.core.model.decode;

import com.costanzopa.meli.quasarmissionchallenge.core.model.exceptions.MessageException;
import java.util.Arrays;

public class RebelDecoderMethod implements DecodeMethod {

  @Override
  public String decode(String[][] messages) throws MessageException {
    //Find the array that is not shifted to the right
    int pivotIndexMessage = findPivotIndex(messages);
    int originalSizeMessage = messages[pivotIndexMessage].length;

    //Shift to the left when it's necessary
    String[][] shiftedMessages = shiftMessages(messages, originalSizeMessage);

    //Merge messages into a String
    return mergeAll(shiftedMessages, originalSizeMessage);
  }


  private int findPivotIndex(String[][] messages) throws MessageException {
    int indexToFind = -1;
    if (messages == null) {
      throw new MessageException("It is impossible to decode the message.");
    }

    for (int i = 0; i < messages.length; i++) {
      String[] message = messages[i];
      if (message.length > 0 && !message[0].isEmpty()) {
        indexToFind = i;
        break;
      }
    }

    if (indexToFind == -1) {
      throw new MessageException(
          "It is impossible to decode the message.You need an unshifted one.");
    }

    return indexToFind;
  }

  public String[][] shiftMessages(String[][] messages,
      int originalSizeMessage) {

    return Arrays.stream(messages)
        .map(message -> message.length >= originalSizeMessage ? subArray(message,
            message.length - originalSizeMessage, message.length) : message)
        .toArray(String[][]::new);
  }

  private String[] subArray(String[] array, int start, int end) {
    return Arrays.copyOfRange(array, start, end);
  }


  private String mergeAll(String[][] shiftedMessages, int originalSizeMessage)
      throws MessageException {
    String[] arrayToMerge = shiftedMessages[0];

    for (int i = 1; i < shiftedMessages.length; i++) {
      String[] currentArray = shiftedMessages[i];
      arrayToMerge = join(arrayToMerge, currentArray);
    }
    if (!checkMessage(arrayToMerge)) {
      throw new MessageException("It is impossible to decode the message.");
    }
    return String.join(" ", arrayToMerge);
  }


  private String[] join(String[] arrayToMerge, String[] currentArray) throws MessageException {
    if (arrayToMerge.length != currentArray.length) {
      throw new MessageException("It is impossible to decode the message.");
    }
    for (int i = 0; i < currentArray.length; i++) {
      arrayToMerge[i] = (!arrayToMerge[i].isEmpty() ? arrayToMerge[i] : currentArray[i]);
    }
    return arrayToMerge;
  }


  private boolean checkMessage(String[] arrayToMerge) {
    return Arrays.stream(arrayToMerge).noneMatch(String::isEmpty);
  }
}
