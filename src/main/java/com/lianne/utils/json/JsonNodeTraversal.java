package com.lianne.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonNodeTraversal {

    private static final String unknownFieldName = "unknown";

    public static @NotNull Map<String, String> traverseJsonNode(@NotNull JsonNode node) {
        Map<String, String> collectibleItemSearchRequestParams = new HashMap<>();
        traverseJsonNodeInternal(node, unknownFieldName, collectibleItemSearchRequestParams);
        return collectibleItemSearchRequestParams;
    }

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