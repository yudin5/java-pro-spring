package org.example.dto.payment;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Данные о платеже
 */
@Data
public class PaymentRequest {

    /**
     * Номер счёта
     */
    private String accountNumber;

    /**
     * Сумма платежа
     */
    private BigDecimal amount;

}
