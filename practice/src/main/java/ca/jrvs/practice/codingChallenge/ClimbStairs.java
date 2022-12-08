package ca.jrvs.practice.codingChallenge;

public class ClimbStairs {

  /**
   * Notion URL: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-8dbdd3db56fe4c29be2f01c1e950264e
   *
   * Big O notation: O(2^n)
   *
   * Justification: Since it seems like getting steps to reach certain
   *                number of stair is similar to fibonacci numbers,
   *                recursion returns O(2^n) time complexity because
   *                as input increases, the number of repetitive function
   *                increases.
   *
   * @param n
   * @return
   */
  public int climbStairsRecur(int n) {
      if(n==0){
        return 0;
      }
      else if(n==1){
        return 1;
      }
      else if(n==2){
        return 2;
      }
      else {
        return climbStairsRecur(n-1) + climbStairsRecur(n-2);
      }
    }

  /**
   * Big O notation: O(n);
   *
   * Justification: Since we saved result to the int array and reuse it every
   *                time we need it, we only need to go through it one time.
   *
   * @param n
   * @return
   */
  public int climbStairsDynamic(int n) {

    int[] steps = new int[n+3];

    steps[0]=0;
    steps[1]=1;
    steps[2]=2;

    for(int i = 3; i <= n; i++){
      steps[i] = steps[i-1] + steps[i-2];
    }

    return steps[n];
  }
}
