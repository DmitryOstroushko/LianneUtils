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

/**
 * !!! Legacy methods using HttpURLConnection are for compatibility but can be deprecated
 * Utility class for executing HTTP requests using {@link HttpURLConnection}.
 * <p>
 * This class provides methods to prepare and execute HTTP requests, allowing communication
 * with external APIs over HTTP(S). The class can handle different HTTP methods, such as GET and POST,
 * and includes functionality to send request parameters as part of the query string.
 * </p>
 * <h4>Legacy Support:</h4>
 * <p>
 * The methods provided are designed for compatibility with older Java versions and might be deprecated
 * in the future in favor of newer HTTP client libraries, such as {@link java.net.http.HttpClient}.
 * </p>
 */
public class HttpURLConnectionRequestExecute {

    /**
     * Executes an HTTP request using {@link HttpURLConnection}.
     * <p>
     * This method prepares an HTTP connection with the specified URL, HTTP method (e.g., GET or POST),
     * API key, and optional query parameters. It sends the request and reads the response if successful.
     * </p>
     * <p>
     * This method is designed to provide a basic way of executing API requests with optional query parameters.
     * </p>
     * <h4>Usage Example:</h4>
     * <pre>
     * Map<String, String> params = Map.of("param1", "value1", "param2", "value2");
     * HttpURLConnectionRequestExecute requestExecutor = new HttpURLConnectionRequestExecute();
     * requestExecutor.executeRequestByHttpURLConnection("http://api.example.com", "GET", "API-Key", "your-api-key", params);
     * </pre>
     *
     * @param url The URL of the API or server to which the request is sent.
     * @param httpMethod The HTTP method to use for the request (e.g., "GET", "POST").
     * @param apiKeyName The name of the API key header.
     * @param apiKeyValue The value of the API key.
     * @param params Optional query parameters to append to the URL.
     */
    public void executeRequestByHttpURLConnection(
            String url,
            String httpMethod,
            String apiKeyName,
            String apiKeyValue,
            @Nullable Map<String,
                    String> params
    ) {
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

    /**
     * Prepares an {@link HttpURLConnection} for the given URL and parameters.
     * <p>
     * This method sets up the connection with the specified HTTP method, API key, and optional parameters.
     * It also configures the connection properties, such as the content type and headers.
     * </p>
     *
     * @param url The URL of the API or server to connect to.
     * @param httpMethod The HTTP method to use (e.g., "GET", "POST").
     * @param apiKeyName The name of the API key header.
     * @param apiKeyValue The value of the API key.
     * @param params Optional query parameters to be appended to the URL.
     * @return A prepared {@link HttpURLConnection} object.
     * @throws IOException If an I/O error occurs while setting up the connection.
     */
    private @NotNull HttpURLConnection prepareHttpConnection(String url, String httpMethod, String apiKeyName, String apiKeyValue, @Nullable Map<String, String> params) throws IOException {
        URI uri = URI.create(url + (params != null ? buildQueryString(params) : ""));
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();

        connection.setRequestMethod(httpMethod);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty(apiKeyName, apiKeyValue);

        return connection;
    }

    /**
     * Builds a query string from the provided parameters.
     * <p>
     * This method converts a map of parameters into a URL-encoded query string that can be appended to a URL.
     * </p>
     *
     * @param params The map of parameters to convert into a query string.
     * @return The query string representing the parameters.
     */
    private @NotNull String buildQueryString(@NotNull Map<String, String> params) {
        return "?" + params.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }

    /**
     * Reads the response from the given HTTP connection.
     * <p>
     * This method reads the response body from the connection's input stream and returns it as a single string.
     * </p>
     *
     * @param connection The HTTP connection from which to read the response.
     * @return The response body as a string.
     * @throws IOException If an I/O error occurs while reading the response.
     */
    private String readResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            return in.lines().collect(Collectors.joining("\n"));
        }
    }

}
