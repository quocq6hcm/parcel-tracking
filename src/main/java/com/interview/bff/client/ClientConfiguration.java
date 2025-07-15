package com.interview.bff.client;

import com.interview.bff.client.guest.GuestClient;
import com.interview.bff.client.parcel.ParcelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class ClientConfiguration {
    private final String _CallerIdentity = "bff";

    @Bean
    public GuestClient guestClient(
        ClientRemote clientProperties,
        RestClient.Builder restClientBuilder,
        SimpleRestClientInterceptor simpleRestClientInterceptor
    ) {
        return HttpServiceProxyFactory.builder()
            .exchangeAdapter(
                RestClientAdapter.create(
                    restClientBuilder
                        .baseUrl(clientProperties.getGuest())
                        .requestFactory(getClientHttpRequestFactory())
                        .requestInterceptor(simpleRestClientInterceptor)
                        .defaultHeader("X-Caller", _CallerIdentity)
                        .build()))
            .build()
            .createClient(GuestClient.class);
    }

    @Bean
    public ParcelClient parcelClient(
        ClientRemote clientProperties,
        RestClient.Builder restClientBuilder,
        SimpleRestClientInterceptor simpleRestClientInterceptor
    ) {
        return HttpServiceProxyFactory.builder()
            .exchangeAdapter(
                RestClientAdapter.create(
                    restClientBuilder
                        .baseUrl(clientProperties.getParcel())
                        .requestFactory(getClientHttpRequestFactory())
                        .requestInterceptor(simpleRestClientInterceptor)
                        .defaultHeader("X-Caller", _CallerIdentity)
                        .build()))
            .build()
            .createClient(ParcelClient.class);
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(Duration.ofMillis(30 * 60 * 1000));
        simpleClientHttpRequestFactory.setReadTimeout(Duration.ofMillis(30 * 60 * 1000));
        return simpleClientHttpRequestFactory;
    }

}

