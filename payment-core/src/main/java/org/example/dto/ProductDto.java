package org.example.dto;

import java.math.BigDecimal;

public record ProductDto(Long id, String accountNumber, BigDecimal balance, ProductType type, Long userId) {

    enum ProductType {
        ACCOUNT, // Счёт
        CARD     // Карта
    }

}
