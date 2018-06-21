package com.fiap.frameworks.clothes.controller;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity customer(@RequestBody CustomerEntity customer) {
        customerService.save(customer);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(customerService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity findById(@PathVariable("id") final Long id) {
        try {
            return ResponseEntity.ok(customerService.findById(id));
        } catch (APIException e) {
            return ResponseEntity.status(e.getStatus()).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
