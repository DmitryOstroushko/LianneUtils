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

@Component
public class RestClientRequestExecute {

    private final RestTemplate restTemplate;

    public RestClientRequestExecute() {
        this.restTemplate = new RestTemplate();
    }

    public RestClientRequestExecute(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
            return response.getBody();
        }
        return null;
    }

}
