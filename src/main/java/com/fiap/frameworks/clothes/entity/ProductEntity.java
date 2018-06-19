package com.fiap.frameworks.clothes.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "PRODUCT")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_PRICE")
    private BigDecimal price;

    @Column(name = "PRODUCT_NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
