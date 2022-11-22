package ca.jrvs.practice.codingChallenge;

import java.util.Map;


public class CompareTwoMaps {

    /**
     * Big O notation: O(n)
     *
     * Justification: Since .equals() method iterate through all the
     *                key from the map to compare key and value, the
     *                time complexity is O(n)
     */
    public <K,V> boolean compareMapsEquals(Map<K,V> m1, Map<K,V> m2){

        boolean similarity = false;

        if (m1.equals(m2)){
            similarity = true;
        }

        return similarity;
    }

    /**
     * Big O notation: O(n)
     *
     * Justification: This method implements equals method in HashJMap
     *                For every key in first map, compare key with
     *                second key and if second key matches first key,
     *                look for whether value of the second key matches
     *                value of the first key.
     */
    public <K,V> boolean compareMapsImpl (Map<K,V> m1, Map<K,V> m2){

        if (m1 == m2){
            return true;
        }
        if (m1.size() != m2.size()){
            return false;
        }
        for (K key: m1.keySet()){
            if (m2.containsKey(key) || m1.get(key).equals(m2.get(key)))
            {
                return true;
            }
        }

        return false;
    }

}
