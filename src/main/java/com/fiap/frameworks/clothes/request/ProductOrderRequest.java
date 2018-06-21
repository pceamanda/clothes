package com.fiap.frameworks.clothes.request;

import java.io.Serializable;

public class ProductOrderRequest implements Serializable {

    private Long productId;
    private Integer amount;

    public Long getProductId() {
        return productId;
    }

    public Integer getAmount() {
        return amount;
    }

}
