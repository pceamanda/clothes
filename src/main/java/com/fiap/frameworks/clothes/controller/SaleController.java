package com.fiap.frameworks.clothes.controller;

import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.service.SaleService;
import com.fiap.frameworks.clothes.utils.Utils;
import com.itextpdf.text.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity sale(SaleEntity sale) {

        String chv = sale.getId() + sale.getCustomer().getId() + String.valueOf(sale.getFullPrice());
        String hash = Utils.generateHash(chv);
        Utils.formatHash(hash);

        saleService.sale(sale);
        return ResponseEntity.ok().build();
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

}
