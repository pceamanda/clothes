package com.fiap.frameworks.clothes.TestRepository;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("it-test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRepositoryCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void createCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName("ABC");
        customer.setCpf("1234");

        Assert.assertTrue(customerRepository.findAll().isEmpty());

        customerRepository.save(customer);
        List<CustomerEntity> customers = customerRepository.findAll();
        Assert.assertFalse(customers.isEmpty());
        Assert.assertEquals(1, customers.size());
    }
}
