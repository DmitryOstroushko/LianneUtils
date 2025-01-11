package com.lianne.utils.map;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class MapCounter {

    public static void counterStringKey(
            String key, @NotNull HashMap<String, Integer> listCount
    ) {
        listCount.compute(key, (k, frequency) -> frequency == null ? 1 : frequency + 1);
    }


    public static void counterIntegerKey(
            Integer key, @NotNull HashMap<Integer, Integer> listCount
    ) {
        listCount.compute(key, (k, frequency) -> frequency == null ? 1 : frequency + 1);
    }

}
