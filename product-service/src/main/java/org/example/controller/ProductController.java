package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.dto.payment.PaymentRequest;
import org.example.dto.payment.PaymentResponse;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all/user/{userId}")
    public List<ProductDto> getAllProductsByUserId(@PathVariable("userId") Long userId) {
        return productService.getAllProductsByUserId(userId);
    }

    @PostMapping("/buy")
    public PaymentResponse buyProduct(@RequestBody PaymentRequest paymentRequest) {
        return productService.buyProduct(paymentRequest);
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

}
