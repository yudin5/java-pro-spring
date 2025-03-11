package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.dto.payment.PaymentRequest;
import org.example.dto.payment.PaymentResponse;
import org.example.exceptions.ClientIntegrationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final RestTemplate restTemplate;

    @Value("${integration.clients.products-client.get-products-url}")
    private String getClientProductsUrl;

    @Value("${integration.clients.products-client.buy-product-url}")
    private String buyProductUrl;

    /**
     * Получить список продуктов по ID клиента
     *
     * @param userId ID клиента
     * @return список продуктов
     */
    public List<ProductDto> getClientProducts(String userId) {

        if (userId == null || userId.isEmpty()) {
            throw new ClientIntegrationException("Некорректно указан ID клиента", HttpStatus.BAD_REQUEST);
        }

        ResponseEntity<List<ProductDto>> exchange = restTemplate.exchange(
                getClientProductsUrl + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    /**
     * Произвести платёж
     *
     * @param paymentRequest платёжный запрос
     * @return ответ по платежу
     */
    public PaymentResponse buyProduct(PaymentRequest paymentRequest) {

        if (paymentRequest.getAccountNumber().isBlank()) {
            throw new ClientIntegrationException("Номер счёта должен быть указан", HttpStatus.BAD_REQUEST);
        }
        if (paymentRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new ClientIntegrationException("Сумма списания не может быть отрицательным числом", HttpStatus.BAD_REQUEST);
        }

        return restTemplate.postForObject(
                buyProductUrl,
                paymentRequest,
                PaymentResponse.class
        );
    }
}
