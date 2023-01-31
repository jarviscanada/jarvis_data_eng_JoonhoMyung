package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {


  /**
   * Notion URL: https://www.notion.so/jarvisdev/Valid-Parentheses-e47a5499f14a46249c1898cfba77be75
   *
   * Big O notation: O(n)
   *
   * Justification: Iterates each character of string only once
   *                Using Map, it saves closing bracket as a key and opening bracket
   *                as a value.
   *                Using stack, push opening brackets and pop from stack
   *                when iterating closing bracket.
   */
  public boolean isValid(String s) {

    boolean isTrue;

    Map<Character, Character> parentheses = new HashMap<>();
    Stack stk = new Stack();
    int counter = 0;

    parentheses.put('}', '{');
    parentheses.put(')', '(');
    parentheses.put(']', '[');

    if(s.length() < 2){
      return false;
    }

    for(char c : s.toCharArray()){
      if(parentheses.containsKey(c)){

        if(stk.isEmpty()){
          return false;
        }

        isTrue = (parentheses.get(c) == stk.pop());
        counter++;

        if(isTrue == false){
          return isTrue;
        }
      }
      else{
        stk.push(c);
      }

    }

    if(counter == 0 || !stk.isEmpty()){
      return false;
    }

    return true;
  }
}
