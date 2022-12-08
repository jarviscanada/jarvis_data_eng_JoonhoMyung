package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {

  /**
   * Notion URL: https://www.notion.so/jarvisdev/Valid-Palindrome-65d8a7a0848d428689463a341caeab19
   *
   * Big O notation: O(n^2)
   *
   * Justification: First, save all the characters which is letter or number
   *                to a string variable. After this, Iterate through
   *                String using two pointers, one from beginning, and one
   *                from the end.
   */
  public boolean isPalindromeTwoPointer(String s) {

    int pointer = 0;

    String lettersOnly = "";

    for(int i = 0; i < s.length(); i++){
      if(Character.isLetterOrDigit(s.charAt(i))) {
        lettersOnly += Character.toLowerCase(s.charAt(i));
      }
    }

    for(int j = 0; j < lettersOnly.length()/2; j++){
      if(lettersOnly.charAt(j) != (lettersOnly.charAt(lettersOnly.length()-j-1))){
        return false;
      }
    }

    return true;
  }

  /**
   * Big O notation: O(n)
   *
   * Justification: n times recursion will be called and It iterate through
   *                each string only once.
   */
  public boolean isPalindromeRecur(String s) {

    if(s.length() <= 1){
      return true;
    }

    if(!Character.isLetterOrDigit(s.charAt(0))){
      return isPalindromeRecur(s.substring(1));
    }
    if(!Character.isLetterOrDigit(s.charAt(s.length()-1))){
      return isPalindromeRecur(s.substring(0,s.length()-1));
    }

    return (Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(s.charAt(s.length()-1)))
                && isPalindromeRecur(s.substring(1,s.length()-1));
  }
}
