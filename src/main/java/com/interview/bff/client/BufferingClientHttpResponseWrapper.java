package com.interview.bff.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

    private final ClientHttpResponse response;
    private final byte[] body;

    public BufferingClientHttpResponseWrapper(ClientHttpResponse response, byte[] body) {
        this.response = response;
        this.body = body;
    }

    @Override
    public InputStream getBody() throws IOException {
        // Return a new InputStream that wraps the buffered body
        return new ByteArrayInputStream(body);
    }

    // Delegate all other methods to the original response
    @Override
    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.valueOf(response.getStatusCode().value());
    }

    @Override
    public String getStatusText() throws IOException {
        return response.getStatusText();
    }

    @Override
    public void close() {
        response.close();
    }

    @Override
    public org.springframework.http.HttpHeaders getHeaders() {
        return response.getHeaders();
    }
}