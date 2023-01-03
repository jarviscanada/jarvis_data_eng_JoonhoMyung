package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
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
