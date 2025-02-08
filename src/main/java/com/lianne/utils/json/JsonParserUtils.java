package com.lianne.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for parsing and extracting JSON from text.
 * This class provides methods to:
 * - Safely parse a string into a JsonNode if the string is valid JSON.
 * - Extract a JSON string from text using regular expressions.
 */
public class JsonParserUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Tries to parse the given string into a JsonNode object.
     * If the string is valid JSON, it returns the parsed JsonNode.
     * If the string is invalid JSON, it catches the exception and returns null.
     *
     * @param text The string to parse as JSON.
     * @return The parsed JsonNode if the string is valid JSON, otherwise null.
     */
    public static @Nullable JsonNode parseJsonIfValid(String text) {
        try {
            // try to convert the string to JsonNode
            return objectMapper.readTree(text);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Extracts a JSON string from the response text.
     * A regular expression searches for a string starting with the '{' character and ending with '}',
     * and returns the matched JSON. If no JSON is found, it returns an empty JSON object ("{}").
     *
     * @param response The response text containing the JSON string.
     * @return The extracted JSON as a string, or "{}" if no JSON is found.
     */
    public static String extractJson(String response) {
        Pattern pattern = Pattern.compile("\\{.*}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(response);
        return matcher.find() ? matcher.group() : "{}";
    }

}