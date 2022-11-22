package ca.jrvs.practice.codingChallenge;

public class FibonacciNumber {

    /**
     * Notion url: https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-8dbdd3db56fe4c29be2f01c1e950264e
     *
     * Big O notation: O(2^n)
     *
     * Justification: For example, when we search Fibonacci number for
     *                complexity fib(5), many fib(n) functions are called
     *                again and again. In fib(5), fib(3) was called 2 times
     *                and fib(2) was called 3 times which harms performance
     *                of the program.
     *
     *                Since every function calls fib(n-1) + fib(n-2) until
     *                it hits the base case, this function is exponential.
     *
     * @param n
     * @return
     */
    public int fibRecur(int n){

        if(n==0){
            return 0;
        }
        else if(n==1){
            return 1;
        }
        else{
          return fibRecur(n-1) + fibRecur(n-2);
        }

    }

    /**
     * Big O notation: O(n)
     *
     * Justification: Since all the function values are saved in array
     *                and we reuse those arrays, functions only need to
     *                be calculated once.
     *
     * @param n
     * @return
     */
    public int fibDynamic(int n){
        int[] fibArr = new int[n+2];

        fibArr[0] = 0;
        fibArr[1] = 1;

        for(int i = 2; i <= n; i++){
            fibArr[i] = fibArr[i-1] + fibArr[i-2];
        }

        return fibArr[n];
    }
}
