package com.lianne.utils.net;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpURLConnectionRequestExecute {

    // Legacy methods using HttpURLConnection are for compatibility but can be deprecated

    public void executeRequestByHttpURLConnection(String url, String httpMethod, String apiKeyName, String apiKeyValue, @Nullable Map<String, String> params) {
        try {
            HttpURLConnection connection = prepareHttpConnection(url, httpMethod, apiKeyName, apiKeyValue, params);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponse(connection);
            }
        } catch (Exception e) {
            System.out.printf("Error during HTTP call: %s", e.getMessage());
        }
    }

    private @NotNull HttpURLConnection prepareHttpConnection(String url, String httpMethod, String apiKeyName, String apiKeyValue, @Nullable Map<String, String> params) throws IOException {
        URI uri = URI.create(url + (params != null ? buildQueryString(params) : ""));
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

        connection.setRequestMethod(httpMethod);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty(apiKeyName, apiKeyValue);

        return connection;
    }

    private @NotNull String buildQueryString(@NotNull Map<String, String> params) {
        return "?" + params.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            return in.lines().collect(Collectors.joining("\n"));
        }
    }

}
