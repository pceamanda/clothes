package com.fiap.frameworks.clothes.controller;

import com.fiap.frameworks.clothes.entity.ProductEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity product(@RequestBody ProductEntity product) {
        productService.save(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(productService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (APIException e) {
            return ResponseEntity.status(e.getStatus()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
