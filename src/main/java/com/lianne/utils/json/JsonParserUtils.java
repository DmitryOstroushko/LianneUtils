package com.lianne.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;

public class JsonParserUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Checks if the string is JSON object and returns: JsonNode object or string if it is not JSON
     *
     * @param text is the string for checking
     * @return JsonNode object or string if it is not JSON
     */
    public static @Nullable JsonNode parseJsonIfValid(String text) {
        try {
            // try to convert the string to JsonNode
            return objectMapper.readTree(text);
        } catch (Exception e) {
            return null;
        }
    }

}