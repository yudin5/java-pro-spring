package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/products")
public class ProductController {

    private final ProductService productService;

    // По условию ДЗ должны быть реализованы только 2 эти метода

    @GetMapping("/all")
    public List<ProductDto> getAllProducts(@RequestParam("userId") Long userId) {
        return productService.getAllProductsByUserId(userId);
    }
    // === Пример вызова ===
    // http://localhost:8080/products-app/v1/api/products/all?userId=1
    // [{"id":1,"accountNumber":"40503810658040000111","balance":1200.00,"type":"ACCOUNT","userId":1},
    // {"id":2,"accountNumber":"40703810900560008222","balance":500.00,"type":"CARD","userId":1},
    // {"id":3,"accountNumber":"40702810500000000333","balance":30000.00,"type":"CARD","userId":1}]

    @GetMapping("/id")
    public ProductDto getProductById(@RequestParam("productId") Long productId) {
        return productService.getProductById(productId);
    }
    // === Пример вызова ===
    // http://localhost:8080/products-app/v1/api/products/id?productId=1
    // {"id":1,"accountNumber":"40503810658040000111","balance":1200.00,"type":"ACCOUNT","userId":1}

}
