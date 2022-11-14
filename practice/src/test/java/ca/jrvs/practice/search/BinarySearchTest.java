package ca.jrvs.practice.search;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;

public class BinarySearchTest {
  BinarySearch binary = new BinarySearch();
  String[] arrays = new String[]{"a", "b", "c", "d", "e", "f", "g"};

  @Test
  public void testBinarySearchIter(){
      assertEquals(Optional.of(0), binary.binarySearchIteration(arrays, "a"));
      assertEquals(Optional.of(1), binary.binarySearchIteration(arrays, "b"));
      assertEquals(Optional.of(2), binary.binarySearchIteration(arrays, "c"));
      assertEquals(Optional.of(3), binary.binarySearchIteration(arrays, "d"));
      assertEquals(Optional.of(4), binary.binarySearchIteration(arrays, "e"));
      assertEquals(Optional.of(5), binary.binarySearchIteration(arrays, "f"));
      assertEquals(Optional.of(6), binary.binarySearchIteration(arrays, "g"));
  }

  @Test
  public void testBinarySearchRecur(){
    assertEquals(Optional.of(0), binary.binarySearchRecursion(arrays, "a"));
    assertEquals(Optional.of(1), binary.binarySearchRecursion(arrays, "b"));
    assertEquals(Optional.of(2), binary.binarySearchRecursion(arrays, "c"));
    assertEquals(Optional.of(3), binary.binarySearchRecursion(arrays, "d"));
    assertEquals(Optional.of(4), binary.binarySearchRecursion(arrays, "e"));
    assertEquals(Optional.of(5), binary.binarySearchRecursion(arrays, "f"));
    assertEquals(Optional.of(6), binary.binarySearchRecursion(arrays, "g"));
  }
}
