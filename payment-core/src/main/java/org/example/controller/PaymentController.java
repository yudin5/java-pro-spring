package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.ProductDto;
import org.example.dto.payment.PaymentRequest;
import org.example.dto.payment.PaymentResponse;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/products/user/{userId}")
    public List<ProductDto> getClientProducts(@PathVariable("userId") String userId) {
        return paymentService.getClientProducts(userId);
    }

    @PostMapping("/products/execute")
    public PaymentResponse buyProduct(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.buyProduct(paymentRequest);
    }

}
