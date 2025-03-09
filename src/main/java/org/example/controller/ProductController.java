package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
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

    @GetMapping("/id")
    public ProductDto getProductById(@RequestParam("productId") Long productId) {
        return productService.getProductById(productId);
    }

}
