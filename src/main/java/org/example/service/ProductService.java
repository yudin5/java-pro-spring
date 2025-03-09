package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

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
