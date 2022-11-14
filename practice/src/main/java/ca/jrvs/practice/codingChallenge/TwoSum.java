package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * Notion URL: https://www.notion.so/jarvisdev/Two-Sum-3c917a5c9e254fab97b237e95fb98328
     *
     * Big O notation: O(n^2)
     *
     * Justification: Since this approach uses two for loops, this method iterates
     *                first array with maximum nums.length-1 times and iterates
     *                second array maximum nums.length-1-i(1) times.
     *                This means (n-1) * (n-2) is the element of O(n^2).
     *
     *
     * @param nums
     * @param target
     * @return
     */
      public int[] twoSumBruteForce(int[] nums, int target) {
          int[] indices = new int[2];

          for(int i = 0; i < nums.length-1; i++){
            for(int j = i+1; j < nums.length; j++){
              if(nums[i]+nums[j] == target){
                indices[0]=i;
                indices[1]=j;
              }
            }
          }

          return indices;
      }

    /**
     * Big O notation: O(n)
     *
     * Justification: HashMap helps method to loop through the array only once.
     *                Therefore, the time complexity is O(n) for this method.
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumHashMap(int[] nums, int target) {
          int[] indices = new int[2];

          Map<Integer, Integer> numsMap = new HashMap<>();

          for(int i = 0; i < nums.length; i++){
              int remainder = target - nums[i];
              if(numsMap.containsKey(remainder)){
                  indices[0] = numsMap.get(remainder);
                  indices[1] = i;
                  break;
              }
              else{
                numsMap.put(nums[i], i);
              }
          }

          return indices;
        }
}
