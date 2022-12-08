package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FibonacciNumberTest {

    FibonacciNumber fibonacci = new FibonacciNumber();

    @Test
    public void fibRecurTest() {
        assertEquals(1,fibonacci.fibRecur(2));
        assertEquals(2,fibonacci.fibRecur(3));
        assertEquals(3,fibonacci.fibRecur(4));
    }

    @Test
    public void fibDynamicTest() {
        assertEquals(1,fibonacci.fibDynamic(2));
        assertEquals(2,fibonacci.fibDynamic(3));
        assertEquals(3,fibonacci.fibDynamic(4));
    }
}
