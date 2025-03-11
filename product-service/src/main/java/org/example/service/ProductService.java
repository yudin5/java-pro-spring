package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.dto.payment.PaymentRequest;
import org.example.dto.payment.PaymentResponse;
import org.example.entity.Product;
import org.example.exceptions.IntegrationException;
import org.example.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Получить все продукты по ID пользователя
     *
     * @param userId ID пользователя
     * @return список ДТО продуктов
     */
    public List<ProductDto> getAllProductsByUserId(Long userId) {
        return productRepository.getAllByUserId(userId).stream()
                .map(product -> new ProductDto( // Возвращаем через примитивный маппинг
                        product.getId(),
                        product.getAccountNumber(),
                        product.getBalance(),
                        product.getType(),
                        product.getUserId()))
                .toList();
    }

    /**
     * Процесс покупки, списания средств со счёта
     *
     * @param paymentRequest платёжный запрос
     * @return ответ по платежу
     */
    @Transactional
    public PaymentResponse buyProduct(PaymentRequest paymentRequest) {
        String accountNumber = paymentRequest.getAccountNumber();
        Product product = productRepository.findByAccountNumber(accountNumber)
                .orElseThrow(
                        () -> new IntegrationException(String.format("Номер счёта [%s] не найден", accountNumber), HttpStatus.BAD_REQUEST)
                );
        BigDecimal initialBalance = product.getBalance();
        BigDecimal paymentAmount = paymentRequest.getAmount();
        if (initialBalance.compareTo(paymentAmount) < 0) {
            throw new IntegrationException("Баланс недостаточен для оплаты", HttpStatus.BAD_REQUEST);
        }
        BigDecimal remainder = initialBalance.subtract(paymentAmount);
        product.setBalance(remainder);
        productRepository.saveAndFlush(product);
        return new PaymentResponse("Payment successful, remainder = " + remainder);
    }

    /**
     * Получить продукт по его ID.
     * Если продукт не найден, то бросаем иключение.
     *
     * @param productId ID продукта
     * @return ДТО продукта
     */
    public ProductDto getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(product -> new ProductDto( // Возвращаем через примитивный маппинг
                        product.getId(),
                        product.getAccountNumber(),
                        product.getBalance(),
                        product.getType(),
                        product.getUserId())
                )
                .orElseThrow(EntityNotFoundException::new);
    }
}
