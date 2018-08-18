package com.fiap.frameworks.clothes.controller;

import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.request.OrderRequest;
import com.fiap.frameworks.clothes.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity order(@RequestBody OrderRequest order) {
        try {
            orderService.sale(order);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (APIException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }

    @GetMapping(path = "invoice")
    public ResponseEntity generateInvoice(
            @RequestParam(name = "saleId", required = false) final Long saleId) {
        try {
            orderService.generateInvoice(saleId);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (APIException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(orderService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "count")
    public ResponseEntity countAll() {
        try {
            return ResponseEntity.ok(orderService.findAll().size());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
