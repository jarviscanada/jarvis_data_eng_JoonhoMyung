package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class CompareTwoMapsTest {

    CompareTwoMaps compare = new CompareTwoMaps();



    @Test
    public void compareMapsEqualsTest(){
        Map<Integer,String> map1 = new HashMap<Integer, String>();
        Map<Integer,String> map2 = new HashMap<Integer, String>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        map2.put(1, "one");
        map2.put(2, "two");
        map2.put(3, "three");

        assertEquals(true, compare.compareMapsEquals(map1, map2));
    }

    @Test
    public void compareMapsImplTest(){
        Map<Integer,String> map1 = new HashMap<Integer, String>();
        Map<Integer,String> map2 = new HashMap<Integer, String>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        map2.put(1, "one");
        map2.put(2, "two");
        map2.put(3, "three");

        assertEquals(true, compare.compareMapsImpl(map1, map2));
    }

}
