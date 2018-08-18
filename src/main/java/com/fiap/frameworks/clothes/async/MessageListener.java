package com.fiap.frameworks.clothes.async;

import com.fiap.frameworks.clothes.response.SaleResponse;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

@Component
public class MessageListener {

    private static final Logger LOGGER = LogManager.getLogger(MessageListener.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "invoice1", containerFactory = "myFactory")
    public void invoice1(SaleResponse sale) {
        LOGGER.info("receiving invoice1 to generate pdf " + sale.getId());
        PDFMaker.generateInvoicePDF(sale);
    }

    @JmsListener(destination = "invoice2", containerFactory = "myFactory")
    public void invoice2(SaleResponse sale) {
        LOGGER.info("receiving invoice2 to generate pdf " + sale.getId());
        PDFMaker.generateInvoicePDF(sale);
    }

    @JmsListener(destination = "invoice3", containerFactory = "myFactory")
    public void invoice3(SaleResponse sale) {
        LOGGER.info("receiving invoice3 to generate pdf " + sale.getId());
        PDFMaker.generateInvoicePDF(sale);
    }

    @JmsListener(destination = "invoice4", containerFactory = "myFactory")
    public void invoice4(SaleResponse sale) {
        LOGGER.info("receiving invoice4 to generate pdf " + sale.getId());
        PDFMaker.generateInvoicePDF(sale);
    }

    @JmsListener(destination = "invoice5", containerFactory = "myFactory")
    public void invoice5(SaleResponse sale) {
        LOGGER.info("receiving invoice5 to generate pdf " + sale.getId());
        PDFMaker.generateInvoicePDF(sale);
    }

    @JmsListener(destination = "orders", containerFactory = "myFactory")
    public void receiveMessage(String sales) {
        LOGGER.info("receiving orders");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        SaleResponse[] salesArray = gson.fromJson(sales, SaleResponse[].class);

        if(salesArray != null){

            int count = 0;

            for (SaleResponse sale: salesArray) {
                if(sale != null){
                    LOGGER.info("sending invoice to queue " + sale.getId());

                    ++count;

                    if(count == 1){
                        jmsTemplate.convertAndSend("invoice1", sale);
                    } else if (count == 2) {
                        jmsTemplate.convertAndSend("invoice2", sale);
                    } else if (count == 3){
                        jmsTemplate.convertAndSend("invoice3", sale);
                    } else if(count == 4){
                        jmsTemplate.convertAndSend("invoice4", sale);
                    } else {
                        jmsTemplate.convertAndSend("invoice5", sale);
                        count = 0;
                    }

                }
            }
        }
    }

}

