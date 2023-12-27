package com.example.swaggerpractice.product.controller;

import com.example.swaggerpractice.product.model.ProductDto;
import com.example.swaggerpractice.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createProduct(ProductDto productDto) {
        return ResponseEntity.ok().body("create product");
    }
}
