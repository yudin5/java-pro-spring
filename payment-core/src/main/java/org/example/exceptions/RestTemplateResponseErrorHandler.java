package org.example.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        IntegrationException integrationException = objectMapper.readValue(response.getBody(), IntegrationException.class);
        // Обработка всех предусмотренных исключений
        if (integrationException.getHttpStatus().is4xxClientError()) {
            throw new ClientIntegrationException(integrationException.getMessage(), integrationException.getHttpStatus());
        } else {
            throw new ServerIntegrationException(integrationException.getMessage(), integrationException.getHttpStatus());
        }
    }
}
