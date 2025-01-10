package com.lianne.utils.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class RestClientRequestExecute {

    private final RestTemplate restTemplate;

    /**
     * Execute GET method by RestTemplate
     *
     * @param url        request URL
     * @param headersMap request headers (optional)
     * @param paramsMap  request parameters (optional)
     * @return response body as String
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

        log.info("REST request sent to {}", uriComponents.toUriString());
        return extractResponseBody(response);
    }

    /**
     * Build UriComponents object for HTTP request with optional parameters
     *
     * @param url    the URL
     * @param params request parameters (optional)
     * @return UriComponents
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
     * Create HttpEntity with optional headers
     *
     * @param headersMap headers map (optional)
     * @return HttpEntity object
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
     * Extract response body from ResponseEntity
     *
     * @param response the ResponseEntity
     * @return response body or null
     */
    private @Nullable String extractResponseBody(@NotNull ResponseEntity<String> response) {
        if (response.getStatusCode() == HttpStatus.OK) {
            log.debug("API Response: {}", response);
            return response.getBody();
        } else {
            log.warn("API Call Failed. Response Code: {}", response.getStatusCode());
        }
        return null;
    }

    // Legacy methods using HttpURLConnection are left unchanged for compatibility but can be deprecated

    public void executeRequestByHttpURLConnection(String url, String httpMethod, String apiKeyName, String apiKeyValue, @Nullable Map<String, String> params) {
        try {
            HttpURLConnection connection = prepareHttpConnection(url, httpMethod, apiKeyName, apiKeyValue, params);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponse(connection);
                log.info("API Response: {}", response);
            } else {
                log.warn("API Call Failed. Response Code: {}", responseCode);
            }
        } catch (Exception e) {
            log.error("Error during HTTP call: {}", e.getMessage(), e);
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
