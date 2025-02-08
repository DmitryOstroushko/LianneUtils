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
 */public class JsonNodeTraversal {

    private static final String unknownFieldName = "unknown";

    /**
     * Recursively traverses a `JsonNode` and extracts its fields as a `Map<String, String>`.
     * This method processes all nodes (objects, arrays, and values) in the JSON tree.
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
            // If the node is an object
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String fieldName = entry.getKey();
                JsonNode childNode = entry.getValue();
                traverseJsonNodeInternal(childNode, fieldName, resultMap);
            }
        } else if (node.isArray()) {
            // If the node is an array
            for (JsonNode item : node) {
                traverseJsonNodeInternal(item, currentFieldName, resultMap);
            }
        } else {
            // If the node is a value
            resultMap.put(currentFieldName, node.asText());
        }
    }

}