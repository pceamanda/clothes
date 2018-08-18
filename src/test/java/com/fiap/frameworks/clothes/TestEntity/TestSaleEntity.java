package com.fiap.frameworks.clothes.TestEntity;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.entity.SaleEntity;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestSaleEntity {

    @Test
    public void testGetterSetter(){
        CustomerEntity c = new CustomerEntity();
        c.setCpf("1234");
        c.setName("frameworks");

        SaleEntity s = new SaleEntity();
        s.setCustomer(c);
        s.setDate(LocalDateTime.now());
        s.setFullPrice(new BigDecimal(20.00));
        s.setSaleProducts(Arrays.asList());
        s.setHash("1234");

        Assert.assertEquals(c, s.getCustomer());
        Assert.assertEquals(LocalDateTime.now(), s.getDate());
        Assert.assertEquals(new BigDecimal(20.00), s.getFullPrice());
        Assert.assertEquals(Arrays.asList(), s.getSaleProducts());
        Assert.assertEquals("1234", s.getHash());
    }
}
