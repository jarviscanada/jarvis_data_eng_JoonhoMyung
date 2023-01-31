package ca.jrvs.practice.codingChallenge;

public class RotateString {

  /**
   * Notion URL: https://www.notion.so/jarvisdev/Rotate-String-ae339d83c5194f22adb0404595fe7349
   *
   * Big O Notation: O(n^2)
   *
   * Justification: Check length of both strings and check whether s+s string
   *                contains goal string inside.
   *
   */
  public boolean rotateString(String s, String goal) {
    if(s.length()!=goal.length()){
      return false;
    }
    if(!(s+s).contains(goal)){
      return false;
    }

    return true;
  }

}
