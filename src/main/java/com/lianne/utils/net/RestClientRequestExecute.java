package com.lianne.utils.net;

import java.util.*;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class for executing HTTP requests using Spring's {@link RestTemplate}.
 * <p>
 * This class provides methods to send GET requests using the {@link RestTemplate} and optionally include
 * headers and parameters in the request. It facilitates communication with remote servers and APIs over HTTP.
 * </p>
 * <p>
 * It uses the Spring Framework's {@link UriComponentsBuilder} for constructing the request URL with parameters,
 * and {@link RestTemplate} for executing the request and handling the response.
 * </p>
 * <h4>Usage Example:</h4>
 * <pre>
 * RestClientRequestExecute restClient = new RestClientRequestExecute();
 * Map<String, String> headers = Map.of("Authorization", "Bearer token");
 * Map<String, String> params = Map.of("param1", "value1", "param2", "value2");
 * String response = restClient.executeGetMethod("http://api.example.com", headers, params);
 * </pre>
 */
@Component
public class RestClientRequestExecute {

    private final RestTemplate restTemplate;

    /**
     * Default constructor initializing a {@link RestTemplate} instance.
     */
    public RestClientRequestExecute() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Constructor to allow passing a custom {@link RestTemplate}.
     * This is useful for mocking or customizing the RestTemplate in tests.
     *
     * @param restTemplate The {@link RestTemplate} instance to be used for making requests.
     */
    public RestClientRequestExecute(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Executes a GET HTTP request using {@link RestTemplate}.
     * <p>
     * This method constructs the URL with optional query parameters, adds any optional headers, and then sends
     * a GET request to the server. It returns the response body as a String if the request is successful (HTTP 200 OK),
     * otherwise returns null.
     * </p>
     *
     * @param url        The URL of the API or server to which the GET request is sent.
     * @param headersMap Optional map of headers to include in the request.
     * @param paramsMap  Optional map of parameters to be appended as query parameters in the URL.
     * @return The response body as a String if the response status is HTTP 200 OK, otherwise null.
     */
    public String executeGetMethod(
            String url,
            @Nullable Map<String, String> headersMap,
            @Nullable Map<String, String> paramsMap)
    {
        // Build UriComponents with or without parameters
        UriComponents uriComponents = buildUriComponents(url, paramsMap);

        // Prepare HttpHeaders if provided
        HttpEntity<String> httpEntity = createHttpEntity(headersMap);

        // Execute GET request
        ResponseEntity<String> response = restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        return extractResponseBody(response);
    }

    /**
     * Builds a {@link UriComponents} object for an HTTP request with optional query parameters.
     * <p>
     * This method uses the {@link UriComponentsBuilder} to construct the full URL, adding query parameters
     * if provided.
     * </p>
     *
     * @param url    The base URL of the request.
     * @param params Optional map of query parameters to include in the URL.
     * @return A fully constructed {@link UriComponents} object, which represents the URL with the parameters.
     */
    private @NotNull UriComponents buildUriComponents(
            String url,
            @Nullable Map<String, String> params
    ) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (params != null && !params.isEmpty()) {
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(
                    params.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, e -> Collections.singletonList(e.getValue())))
            );
            builder.queryParams(multiValueMap);
        }
        return builder.build().encode();
    }

    /**
     * Creates an {@link HttpEntity} object with optional headers.
     * <p>
     * This method creates an {@link HttpEntity} that includes any provided headers. The content type is set
     * to "application/json" by default.
     * </p>
     *
     * @param headersMap Optional map of headers to be added to the request.
     * @return An {@link HttpEntity} containing the headers (or an empty entity if no headers are provided).
     */
    private @NotNull HttpEntity<String> createHttpEntity(
            @Nullable Map<String, String> headersMap
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        if (headersMap != null) {
            headersMap.forEach(headers::add);
        }
        return new HttpEntity<>(headers);
    }

    /**
     * Extracts the response body from a {@link ResponseEntity}.
     * <p>
     * This method checks if the response status is HTTP 200 OK and returns the body of the response as a string.
     * If the status is not HTTP 200 OK, it returns null.
     * </p>
     *
     * @param response The {@link ResponseEntity} object containing the response data.
     * @return The response body as a String if the response code is 200 OK, otherwise null.
     */
    private @Nullable String extractResponseBody(@NotNull ResponseEntity<String> response) {
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

}
