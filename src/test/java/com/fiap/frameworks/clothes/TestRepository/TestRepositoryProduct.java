package com.fiap.frameworks.clothes.TestRepository;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.entity.ProductEntity;
import com.fiap.frameworks.clothes.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepositoryProduct {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void createProduct() {
        ProductEntity product = new ProductEntity();
        product.setName("ABC");
        product.setPrice(new BigDecimal(20.00));

        Assert.assertTrue(((List<ProductEntity>) productRepository.findAll()).isEmpty());

        productRepository.save(product);
        List<ProductEntity> products = (List<ProductEntity>) productRepository.findAll();
        Assert.assertFalse(products.isEmpty());
        Assert.assertEquals(1, products.size());
    }
}
