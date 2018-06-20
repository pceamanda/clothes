package com.fiap.frameworks.clothes.service;

import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sale(SaleEntity sale){
        repository.save(sale);
    }

    public void generateInvoice(final Long saleId) throws APIException {

        List<SaleEntity> sales = new ArrayList<>();

        if(saleId != null){
            sales.add(repository.findById(saleId).orElseThrow(
                    () -> new APIException(HttpStatus.BAD_REQUEST)));
        } else {
            sales.addAll(repository.findAll());
        }

        try {
            sales.forEach(sale ->
                jmsTemplate.convertAndSend("invoice", sale)
            );
        } catch (Exception e) {
            throw new APIException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
