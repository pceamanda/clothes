package com.fiap.frameworks.clothes.TestEntity;

import com.fiap.frameworks.clothes.entity.ProductEntity;
import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.entity.SaleProductEntity;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TestSaleProductEntity {

    @Test
    public void testGettersSetters(){
        SaleProductEntity sp = new SaleProductEntity();
        ProductEntity p = new ProductEntity();
        SaleEntity s = new SaleEntity();
        sp.setPrice(new BigDecimal(20.00));
        sp.setProduct(p);
        sp.setSale(s);

        Assert.assertEquals(new BigDecimal(20.00), sp.getPrice());
        Assert.assertEquals(p, sp.getProduct());
        Assert.assertEquals(s, sp.getSale());
    }
}
