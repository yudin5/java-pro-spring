package org.example.dto;

import org.example.entity.enums.ProductType;

import java.math.BigDecimal;

public record ProductDto(Long id, String accountNumber, BigDecimal balance, ProductType type, Long userId) {
}
