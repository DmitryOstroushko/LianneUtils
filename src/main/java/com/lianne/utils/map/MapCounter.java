package com.lianne.utils.map;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Utility class for counting the frequency of keys in a map.
 * <p>
 * This class provides methods for updating the frequency count of keys in a map.
 * It supports both `String` and `Integer` keys and allows tracking the occurrences
 * of these keys in a `HashMap` where the key is the object to be counted,
 * and the value is the frequency of its occurrence.
 * </p>
 */
public class MapCounter {

    /**
     * Increments the count of a given `String` key in the provided map.
     * <p>
     * If the key is already present in the map, its count is incremented by 1.
     * If the key is not present, it is added to the map with an initial count of 1.
     * </p>
     *
     * <h4>Usage Example:</h4>
     * <pre>
     * HashMap<String, Integer> countMap = new HashMap<>();
     * MapCounter.counterStringKey("apple", countMap);
     * MapCounter.counterStringKey("apple", countMap);
     * System.out.println(countMap); // Output: {apple=2}
     * </pre>
     *
     * @param key The `String` key whose frequency is to be counted.
     * @param listCount The map where the frequency of keys is tracked.
     */
    public static void counterStringKey(
            String key, @NotNull HashMap<String, Integer> listCount
    ) {
        listCount.compute(key, (k, frequency) -> frequency == null ? 1 : frequency + 1);
    }

    /**
     * Increments the count of a given `Integer` key in the provided map.
     * <p>
     * If the key is already present in the map, its count is incremented by 1.
     * If the key is not present, it is added to the map with an initial count of 1.
     * </p>
     *
     * <h4>Usage Example:</h4>
     * <pre>
     * HashMap<Integer, Integer> countMap = new HashMap<>();
     * MapCounter.counterIntegerKey(10, countMap);
     * MapCounter.counterIntegerKey(10, countMap);
     * System.out.println(countMap); // Output: {10=2}
     * </pre>
     *
     * @param key The `Integer` key whose frequency is to be counted.
     * @param listCount The map where the frequency of keys is tracked.
     */
    public static void counterIntegerKey(
            Integer key, @NotNull HashMap<Integer, Integer> listCount
    ) {
        listCount.compute(key, (k, frequency) -> frequency == null ? 1 : frequency + 1);
    }

}
