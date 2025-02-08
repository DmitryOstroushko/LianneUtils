package com.lianne.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility class for traversing and extracting data from a `JsonNode` structure.
 * This class provides methods for recursively traversing a JSON tree and extracting
 * information from it in the form of a `Map<String, String>`.
 * <p>
 * The methods in this class process various types of JSON nodes (objects, arrays, and values) and
 * extract field names and values in a flattened key-value structure, where keys are field names,
 * and values are the corresponding field values in text form.
 * </p>
 */
public class JsonNodeTraversal {

    private static final String unknownFieldName = "unknown";

    /**
     * Recursively traverses a `JsonNode` structure and extracts its fields as a `Map<String, String>`.
     * This method processes all nodes (objects, arrays, and value nodes) in the JSON tree.
     * Each field name and its corresponding value are added to the resulting map.
     * <p>
     * This method will process nested objects and arrays, flattening them into a map with
     * string keys representing the field names and string values representing the field values.
     * </p>
     *
     * <h4>Usage:</h4>
     * <pre>
     * JsonNode jsonNode = ... // Obtain your JsonNode
     * Map<String, String> result = JsonNodeTraversal.traverseJsonNode(jsonNode);
     * result.forEach((field, value) -> {
     *     System.out.println(field + ": " + value);
     * });
     * </pre>
     *
     * @param node The root `JsonNode` structure to be traversed.
     * @return A `Map<String, String>` containing all field names and their respective values
     *         from the JSON structure.
     */
    public static @NotNull Map<String, String> traverseJsonNode(@NotNull JsonNode node) {
        Map<String, String> collectibleItemSearchRequestParams = new HashMap<>();
        traverseJsonNodeInternal(node, unknownFieldName, collectibleItemSearchRequestParams);
        return collectibleItemSearchRequestParams;
    }

    /**
     * Internal recursive method that processes the current `JsonNode` and traverses through its fields.
     * It handles objects, arrays, and value nodes recursively, adding field names and their values to the result map.
     *
     * This method is used internally to handle the recursive traversal of the JSON structure.
     * It ensures that nested objects and arrays are processed and their values are collected.
     * <p>
     * Note that the method does not overwrite existing entries in the result map; it adds new key-value
     * pairs as it encounters new fields.
     * </p>
     *
     * <h4>Usage:</h4>
     * This method is not intended to be called directly by users; instead, the public method
     * `traverseJsonNode` should be used.
     *
     * @param node The current `JsonNode` being processed.
     * @param currentFieldName The name of the current field being processed.
     * @param resultMap The map where field names and values are collected.
     */
    private static void traverseJsonNodeInternal(
            @NotNull JsonNode node,
            @NotNull String currentFieldName,
            @NotNull Map<String, String> resultMap
    ) {
        if (node.isObject()) {
            // If the node is an object, traverse its fields
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String fieldName = entry.getKey();
                JsonNode childNode = entry.getValue();
                traverseJsonNodeInternal(childNode, fieldName, resultMap);
            }
        } else if (node.isArray()) {
            // If the node is an array, traverse each element
            for (JsonNode item : node) {
                traverseJsonNodeInternal(item, currentFieldName, resultMap);
            }
        } else {
            // If the node is a value, add it to the result map
            resultMap.put(currentFieldName, node.asText());
        }
    }

}