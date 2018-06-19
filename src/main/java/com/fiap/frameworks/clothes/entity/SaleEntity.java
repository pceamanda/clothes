package com.fiap.frameworks.clothes.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SALE_ID")
    private Long id;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleProductEntity> saleproducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @Column(name = "SALE_DATE")
    private LocalDateTime date;

    @Column(name = "SALE_COO")
    private Long coo;

    @Column(name = "SALE_HASH")
    private String hash;

    @Column(name = "SALE_FULL_PRICE")
    private BigDecimal fullPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SaleProductEntity> getSaleproducts() {
        return saleproducts;
    }

    public void setSaleproducts(List<SaleProductEntity> saleproducts) {
        this.saleproducts = saleproducts;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getCoo() {
        return coo;
    }

    public void setCoo(Long coo) {
        this.coo = coo;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }
}
