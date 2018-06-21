package com.fiap.frameworks.clothes.response;

import com.fiap.frameworks.clothes.entity.SaleProductEntity;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleProductResponse implements Serializable {

    private Long productId;

    private String productName;

    private Integer amount;

    private BigDecimal productUnitPrice;

    private BigDecimal price;

    public SaleProductResponse(){}

    public SaleProductResponse(SaleProductEntity saleProduct) {
        this.productId = saleProduct.getProduct().getId();
        this.productName = saleProduct.getProduct().getName();
        this.amount = saleProduct.getAmount();
        this.productUnitPrice = saleProduct.getProduct().getPrice();
        this.price = saleProduct.getPrice();
    }

    public String getProductName() {
        return productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getProductUnitPrice() {
        return productUnitPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getProductId() {
        return productId;
    }
}
