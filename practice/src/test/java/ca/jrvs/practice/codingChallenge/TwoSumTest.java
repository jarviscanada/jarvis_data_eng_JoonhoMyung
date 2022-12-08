package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TwoSumTest {

    TwoSum twoSum = new TwoSum();

    int[] arr1 = new int[]{2,7,11,15};
    int[] arr2 = new int[]{3,2,4};
    int[] arr3 = new int[]{3,3};
    int[] test1 = new int[]{0,1};
    int[] test2 = new int[]{1,2};

    @Test
    public void testTwoSumBruteForce(){
        assertArrayEquals(test1, twoSum.twoSumBruteForce(arr1, 9));
        assertArrayEquals(test2, twoSum.twoSumBruteForce(arr2, 6));
        assertArrayEquals(test1, twoSum.twoSumBruteForce(arr3, 6));
    }

    @Test
    public void testTwoSumHashMap(){
        assertArrayEquals(test1, twoSum.twoSumHashMap(arr1, 9));
        assertArrayEquals(test2, twoSum.twoSumHashMap(arr2, 6));
        assertArrayEquals(test1, twoSum.twoSumHashMap(arr3, 6));
    }
}
