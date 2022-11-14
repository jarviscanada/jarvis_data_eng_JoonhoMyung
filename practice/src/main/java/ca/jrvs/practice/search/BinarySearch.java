package ca.jrvs.practice.search;

import java.util.Comparator;
import java.util.Optional;

public class BinarySearch {

  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchRecursion(E[] arr, E target) {
    int lower = 0;
    int fullIndex = arr.length - 1;

    if(arr.length >=1)
      return binarySearchRecursionWithArguments(arr, target, lower, fullIndex);

    return Optional.empty();
  }

  public <E extends Comparable> Optional<Integer> binarySearchRecursionWithArguments(E[] arr, E target, int lower, int fullIndex) {
    int med = (lower + fullIndex) / 2;

    if(arr[med].compareTo(target) == 0){
        return Optional.of(med);
    }
    else if (arr[med].compareTo(target) > 0){
        return binarySearchRecursionWithArguments(arr, target, lower, med-1);
    }
    else {
        return binarySearchRecursionWithArguments(arr, target, med+1, fullIndex);
    }
  }


  /**
   * find the the target index in a sorted array
   *
   * @param arr input arry is sorted
   * @param target value to be searched
   * @return target index or Optional.empty() if not ound
   */
  public <E extends Comparable> Optional<Integer> binarySearchIteration(E[] arr, E target) {
    int lower = 0;
    int fullIndex = arr.length - 1;

    while (lower <= fullIndex){
      int med = (lower + fullIndex) / 2;

      if (arr[med] == target){
          return Optional.of(med);
      }
      else if(arr[med].compareTo(target) > 0){
          fullIndex = med-1;
      }
      else{
          lower = med+1;
      }
    }

    return Optional.empty();
  }

}