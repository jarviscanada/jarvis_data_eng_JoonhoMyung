package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidParenthesesTest {

  ValidParentheses parentheses = new ValidParentheses();

  @Test
  public void parenthesesTest(){
    assertEquals(true, parentheses.isValid("()"));
    assertEquals(true, parentheses.isValid("()[]{}"));
    assertEquals(false, parentheses.isValid("(]"));
    assertEquals(true, parentheses.isValid("{[]}"));
    assertEquals(false, parentheses.isValid("["));
    assertEquals(false, parentheses.isValid("(("));
  }
}
