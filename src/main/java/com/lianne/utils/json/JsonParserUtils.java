package com.lianne.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for parsing and extracting JSON from text.
 * <p>
 * This class provides methods to:
 * - Safely parse a string into a JsonNode if the string is valid JSON.
 * - Extract a JSON string from text using regular expressions.
 * </p>
 * <h4>Key Features:</h4>
 * <ul>
 *   <li><b>parseJsonIfValid:</b> Attempts to parse a string as JSON and safely returns a JsonNode, or null if invalid.</li>
 *   <li><b>extractJson:</b> Uses regular expressions to extract a valid JSON string from a given response text.</li>
 * </ul>
 * <p>
 * The class is useful for cases where the JSON format may not be guaranteed, and handling errors gracefully is necessary.
 * </p>
 */
public class JsonParserUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Utility class for parsing and extracting JSON from text.
     * <p>
     * This class provides methods to:
     * - Safely parse a string into a JsonNode if the string is valid JSON.
     * - Extract a JSON string from text using regular expressions.
     * </p>
     * <h4>Key Features:</h4>
     * <ul>
     *   <li><b>parseJsonIfValid:</b> Attempts to parse a string as JSON and safely returns a JsonNode, or null if invalid.</li>
     *   <li><b>extractJson:</b> Uses regular expressions to extract a valid JSON string from a given response text.</li>
     * </ul>
     * <p>
     * The class is useful for cases where the JSON format may not be guaranteed, and handling errors gracefully is necessary.
     * </p>
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
     * Utility class for parsing and extracting JSON from text.
     * <p>
     * This class provides methods to:
     * - Safely parse a string into a JsonNode if the string is valid JSON.
     * - Extract a JSON string from text using regular expressions.
     * </p>
     * <h4>Key Features:</h4>
     * <ul>
     *   <li><b>parseJsonIfValid:</b> Attempts to parse a string as JSON and safely returns a JsonNode, or null if invalid.</li>
     *   <li><b>extractJson:</b> Uses regular expressions to extract a valid JSON string from a given response text.</li>
     * </ul>
     * <p>
     * The class is useful for cases where the JSON format may not be guaranteed, and handling errors gracefully is necessary.
     * </p>
     */
    public static String extractJson(String response) {
        Pattern pattern = Pattern.compile("\\{.*}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(response);
        return matcher.find() ? matcher.group() : "{}";
    }

}