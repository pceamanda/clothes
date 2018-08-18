package com.fiap.frameworks.clothes.async;

import com.fiap.frameworks.clothes.response.SaleResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger LOGGER = LogManager.getLogger(MessageListener.class);

    @JmsListener(destination = "invoice", containerFactory = "myFactory")
    public void receiveMessage(SaleResponse sale) {
        LOGGER.info("receiving invoice to generate pdf " + sale.getId());
        PDFMaker.generateInvoicePDF(sale);
    }

}
