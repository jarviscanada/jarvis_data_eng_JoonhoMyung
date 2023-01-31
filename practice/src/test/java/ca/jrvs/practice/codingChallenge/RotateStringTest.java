package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RotateStringTest {

  RotateString rotateString = new RotateString();

  @Test
  public void rotateStringTest() {
    assertEquals(true, rotateString.rotateString("abcde", "cdeab"));
    assertEquals(false, rotateString.rotateString("abcde", "abced"));
  }
}
