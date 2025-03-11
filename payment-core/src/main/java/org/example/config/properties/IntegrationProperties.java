package org.example.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integration.clients")
public class IntegrationProperties {

    private final RestTemplateProperties productsClient;

    @ConstructorBinding
    public IntegrationProperties(RestTemplateProperties productsClient) {
        this.productsClient = productsClient;
    }

    public RestTemplateProperties getProductsClientProperties() {
        return productsClient;
    }

}
