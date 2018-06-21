package com.fiap.frameworks.clothes.controller;

import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.request.OrderRequest;
import com.fiap.frameworks.clothes.service.SaleService;
import com.fiap.frameworks.clothes.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/order")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity order(@RequestBody OrderRequest order) {
        try {
            saleService.sale(order);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (APIException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }

    @GetMapping(path = "invoice")
    public ResponseEntity generateInvoice(
            @RequestParam(name = "saleId", required = false) final Long saleId) {
        try {
            saleService.generateInvoice(saleId);
            return ResponseEntity.ok(Boolean.TRUE);
        } catch (APIException e) {
            return ResponseEntity.status(e.getStatus()).build();
        }
    }

    @GetMapping
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(saleService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
