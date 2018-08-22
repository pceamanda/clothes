package com.fiap.frameworks.clothes.async;

import com.fiap.frameworks.clothes.response.SaleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageListener {

    private static final Logger LOGGER = LogManager.getLogger(MessageListener.class);

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageListener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = "invoice", containerFactory = "myFactory")
    public void invoice(SaleResponse sale) {
        LOGGER.info("receiving invoice to generate pdf " + sale.getId());
        PDFMaker.generateInvoicePDF(sale);
    }

    @JmsListener(destination = "orders", containerFactory = "myFactory")
    public void receiveMessage(String sales) {
        LOGGER.info("receiving orders");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        SaleResponse[] salesArray = gson.fromJson(sales, SaleResponse[].class);

        List<SaleResponse> listResponse = Arrays.asList(salesArray);

        listResponse.stream().parallel().forEach(sale -> {
            LOGGER.info("sending invoice to queue " + sale.getId());
            jmsTemplate.convertAndSend("invoice", sale);
        });
    }

}

