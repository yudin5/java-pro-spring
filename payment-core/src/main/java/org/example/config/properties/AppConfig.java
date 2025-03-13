package org.example.config.properties;

import org.example.exceptions.RestTemplateResponseErrorHandler;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationPropertiesScan
public class AppConfig {

    private final IntegrationProperties integrationProperties;

    public AppConfig(IntegrationProperties integrationProperties) {
        this.integrationProperties = integrationProperties;
    }

    @Bean
    public RestTemplate productsClient(RestTemplateResponseErrorHandler errorHandler) {
        RestTemplateProperties properties = integrationProperties.getProductsClientProperties();
        return new RestTemplateBuilder()
                .rootUri(properties.getUrl())
                .setConnectTimeout(properties.getConnectTimeout())
                .setReadTimeout(properties.getReadTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
