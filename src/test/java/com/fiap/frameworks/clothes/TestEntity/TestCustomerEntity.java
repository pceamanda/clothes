package com.fiap.frameworks.clothes.TestEntity;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import org.junit.Assert;
import org.junit.Test;

public class TestCustomerEntity {

    @Test
    public void testGettersAndSetters(){
        CustomerEntity c = new CustomerEntity();
        c.setCpf("1234");
        c.setName("frameworks");

        Assert.assertEquals("1234", c.getCpf());
        Assert.assertEquals("frameworks", c.getName());
    }
}
