package com.fiap.frameworks.clothes.request;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {

    private Long customerId;

    private Long coo;

    private List<ProductOrderRequest> productOrderRequests;

    public Long getCustomerId() {
        return customerId;
    }

    public Long getCoo() {
        return coo;
    }

    public List<ProductOrderRequest> getProductOrderRequests() {
        return productOrderRequests;
    }

}
