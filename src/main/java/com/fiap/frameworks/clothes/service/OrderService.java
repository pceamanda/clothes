package com.fiap.frameworks.clothes.service;

import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.request.OrderRequest;
import com.fiap.frameworks.clothes.response.SaleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    @Autowired
    private SaleService saleService;

    @Autowired
    private JmsTemplate jmsTemplate;

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

        try {
            LOGGER.info("send orders to queue");
            List<SaleResponse> orders = new ArrayList<>();
            sales.stream().parallel().forEach(sale ->
                orders.add(new SaleResponse(sale))
            );

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            jmsTemplate.convertAndSend("orders", gson.toJson(orders));

        } catch (Exception e) {
            throw new APIException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<SaleResponse> findAll() {
        List<SaleResponse> response = new ArrayList<>();
        saleService.findAll().forEach(sale -> response.add(new SaleResponse(sale)));
        return response;
    }

}
