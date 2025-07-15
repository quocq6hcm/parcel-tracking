package com.interview.bff.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Log4j2
@RequiredArgsConstructor
public class SimpleRestClientInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(
        HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (log.isInfoEnabled()) {
            log.info(
                "Executing request method: {} - uri: {}, body: {}",
                request.getMethod(),
                request.getURI(),
                new String(body, StandardCharsets.UTF_8));
        }
        ClientHttpResponse response = execution.execute(request, body);
        byte[] responseBodyBytes = response.getBody().readAllBytes();
        if (log.isInfoEnabled()) {
            log.info("Response status: {} - {}", response.getStatusCode(), new String(responseBodyBytes, StandardCharsets.UTF_8));
        }

        return new BufferingClientHttpResponseWrapper(response, responseBodyBytes);
    }
}
