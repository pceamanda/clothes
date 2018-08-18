package com.fiap.frameworks.clothes.TestRepository;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.entity.SaleProductEntity;
import com.fiap.frameworks.clothes.repository.SaleProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
        List<SaleProductEntity> products = (List<SaleProductEntity>) saleProductRepository.findAll();
        Assert.assertFalse(products.isEmpty());
        Assert.assertEquals(1, products.size());
    }
}
