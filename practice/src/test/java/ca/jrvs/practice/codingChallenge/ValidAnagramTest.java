package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidAnagramTest {

  ValidAnagram valid = new ValidAnagram();
  String s1 = "anagram";
  String t1 = "nagaram";
  String s2 = "rat";
  String t2 = "car";

  @Test
  public void isAnagramSortingTest(){
    assertEquals(true, valid.isAnagramSorting(s1,t1));
    assertEquals(false, valid.isAnagramSorting(s2,t2));
  }

  @Test
  public void isAnagramHashMapTest(){
    assertEquals(true, valid.isAnagramHashMap(s1,t1));
    assertEquals(false, valid.isAnagramHashMap(s2,t2));
  }
}
