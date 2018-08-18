package com.fiap.frameworks.clothes.TestEntity;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.entity.ProductEntity;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TestProductEntity {

    @Test
    public void testGettersAndSetters(){
        ProductEntity p = new ProductEntity();
        p.setName("Shirt");
        p.setPrice(new BigDecimal(1.0));

        Assert.assertEquals(new BigDecimal(1.0), p.getPrice());
        Assert.assertEquals("Shirt", p.getName());
    }
}
