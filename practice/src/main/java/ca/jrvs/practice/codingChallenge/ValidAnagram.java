package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

  /**
   * Notion URL: https://www.notion.so/jarvisdev/Valid-Anagram-9482b92612e840e99c998d1e547d2008
   *
   * Big O Notation: O(n log(n))
   *
   * Justification: Since Arrays.sort uses dual-pivot quick sorting,
   *                it means the time complexity of this method is
   *                the time complexity of Quick sort which is
   *                O(n log(n))
   */
  public boolean isAnagramSorting(String s, String t) {


    if(s.length()!=t.length()){
      return false;
    }

    char[] sCharArr = s.toCharArray();
    char[] tCharArr = t.toCharArray();

    Arrays.sort(sCharArr);
    Arrays.sort(tCharArr);

    for(int i = 0; i < sCharArr.length; i++){
      if(sCharArr[i] != tCharArr[i]){
        return false;
      }
    }

    return true;

  }

  /**
   * Big O notation:O(n)
   *
   * Justification: Since this method uses hashmap, this method only need
   *                to iterate the char array once to count number of
   *                occurrence of each character.
   */
  public boolean isAnagramHashMap(String s, String t) {

    if(s.length()!=t.length()){
      return false;
    }

    Map<Character, Integer> sMap = new HashMap<>();
    Map<Character, Integer> tMap = new HashMap<>();

    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();

    for(int i=0; i<sArr.length; i++){

      if(sMap.get(sArr[i]) == null) {
        sMap.put(sArr[i], 1);
      }
      else{
        int count = sMap.get(sArr[i]);
        sMap.put(sArr[i], count++);
      }

      if(tMap.get(tArr[i]) == null) {
        tMap.put(tArr[i], 1);
      }
      else{
        int count = tMap.get(tArr[i]);
        tMap.put(tArr[i], count++);
      }
    }


    if(sMap.equals(tMap)){
      return true;
    }
    else{
      return false;
    }


  }
}
