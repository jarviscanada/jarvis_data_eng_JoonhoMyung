package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidPalindromeTest {

    ValidPalindrome valid = new ValidPalindrome();
    String s1 = "A man, a plan, a canal: Panama";
    String s2 = "race a car";
    String s3 = " ";

    @Test
    public void validPalindromeTwoPointerTest(){
      assertEquals(true, valid.isPalindromeTwoPointer(s1));
      assertEquals(false, valid.isPalindromeTwoPointer(s2));
      assertEquals(true, valid.isPalindromeTwoPointer(s3));
    }

    @Test
    public void validPalindromeRecurTest(){
      assertEquals(true, valid.isPalindromeRecur(s1));
      assertEquals(false, valid.isPalindromeRecur(s2));
      assertEquals(true, valid.isPalindromeRecur(s3));
    }
}
