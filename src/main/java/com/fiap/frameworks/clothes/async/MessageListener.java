package com.fiap.frameworks.clothes.async;

import com.fiap.frameworks.clothes.entity.SaleEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @JmsListener(destination = "invoice", containerFactory = "myFactory")
    public void receiveMessage(SaleEntity sale) {
        System.out.println("Received <" + sale.getHash() + ">");
    }

}
