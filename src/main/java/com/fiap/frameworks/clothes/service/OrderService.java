package com.fiap.frameworks.clothes.service;

import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.request.OrderRequest;
import com.fiap.frameworks.clothes.response.SaleResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final Logger LOGGER = LogManager.getLogger(SaleService.class);

    private final SaleService saleService;

    private final JmsTemplate jmsTemplate;

    @Autowired
    public OrderService(SaleService saleService, JmsTemplate jmsTemplate) {
        this.saleService = saleService;
        this.jmsTemplate = jmsTemplate;
    }

    public void sale(OrderRequest order) throws APIException {
        saleService.sale(order);
    }

    public void generateInvoice(final Long saleId) throws APIException {

        List<SaleEntity> sales = new ArrayList<>();

        if(saleId != null){
            sales.add(saleService.findById(saleId).orElseThrow(
                    () -> new APIException(HttpStatus.BAD_REQUEST)));
        } else {
            sales.addAll(saleService.findAll());
        }

        if(sales.isEmpty()){
            throw new APIException(HttpStatus.NO_CONTENT);
        }

        LOGGER.info("send orders to queue");
        sales.stream().parallel().forEach(sale -> {
            LOGGER.info("send to queue to generate pdf " + sale.getId());
            jmsTemplate.convertAndSend("invoice", new SaleResponse(sale));
        });
    }

    public List<SaleResponse> findAll() {
        List<SaleResponse> response = new ArrayList<>();
        saleService.findAll().forEach(sale -> response.add(new SaleResponse(sale)));
        return response;
    }

}
