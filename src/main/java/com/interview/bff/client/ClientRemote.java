package com.interview.bff.client;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "client.url")
@NoArgsConstructor
@Data
public class ClientRemote {
    private String guest;
    private String parcel;
}
