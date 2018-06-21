package com.fiap.frameworks.clothes.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fiap.frameworks.clothes.entity.SaleEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleResponse implements Serializable {

    private Long id;

    private String customerName;

    private String customerCPF;

    private BigDecimal fullPrice;

    private Long coo;

    private String hash;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    private List<SaleProductResponse> saleProductsResponse;

    public SaleResponse(){}

    public SaleResponse(SaleEntity sale) {
        this.id = sale.getId();
        this.customerName = sale.getCustomer().getName();
        this.fullPrice = sale.getFullPrice();
        this.coo = sale.getCoo();
        this.hash = sale.getHash();
        this.date = sale.getDate();
        this.customerCPF = sale.getCustomer().getCpf();

        this.saleProductsResponse = new ArrayList<>();

        sale.getSaleProducts().forEach(saleProduct -> {
            saleProductsResponse.add(new SaleProductResponse(saleProduct));
        });
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public Long getCoo() {
        return coo;
    }

    public String getHash() {
        return hash;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<SaleProductResponse> getSaleProductsResponse() {
        return saleProductsResponse;
    }

    public String getCustomerCPF() {
        return customerCPF;
    }
}

