package com.fiap.frameworks.clothes.TestRepository;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.entity.ProductEntity;
import com.fiap.frameworks.clothes.entity.SaleEntity;
import com.fiap.frameworks.clothes.repository.SaleRepository;
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
public class TestRepositorySale {

    @Autowired
    SaleRepository saleRepository;

    @Test
    public void createSale() {
        SaleEntity s = new SaleEntity();
        CustomerEntity c = new CustomerEntity();
        s.setCoo(1L);
        s.setHash("124");
        s.setFullPrice(new BigDecimal(20.00));
        s.setDate(LocalDateTime.now());

        saleRepository.save(s);
        List<SaleEntity> products = (List<SaleEntity>) saleRepository.findAll();
        Assert.assertFalse(products.isEmpty());
        Assert.assertEquals(1, products.size());
    }
}
