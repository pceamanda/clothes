package com.fiap.frameworks.clothes.TestRepository;

import com.fiap.frameworks.clothes.entity.SaleProductEntity;
import com.fiap.frameworks.clothes.repository.SaleProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@ActiveProfiles("it-test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepositorySaleProduct {

    @Autowired
    SaleProductRepository saleProductRepository;

    @Test
    public void createSaleProduct() {
        SaleProductEntity sp = new SaleProductEntity();
        sp.setPrice(new BigDecimal(20.00));
        sp.setAmount(1);

        saleProductRepository.save(sp);
        List<SaleProductEntity> products = saleProductRepository.findAll();
        Assert.assertFalse(products.isEmpty());
        Assert.assertEquals(1, products.size());
    }
}
