package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClimbStairsTest {

    ClimbStairs climb = new ClimbStairs();

    @Test
    public void testClimbStairsRecur(){
        assertEquals(2, climb.climbStairsRecur(2));
        assertEquals(3, climb.climbStairsRecur(3));
        assertEquals(5, climb.climbStairsRecur(4));
        assertEquals(8, climb.climbStairsRecur(5));
    }

    @Test
    public void testClimbStairsDynamic(){
        assertEquals(2, climb.climbStairsDynamic(2));
        assertEquals(3, climb.climbStairsDynamic(3));
        assertEquals(5, climb.climbStairsDynamic(4));
        assertEquals(8, climb.climbStairsDynamic(5));
    }

}
