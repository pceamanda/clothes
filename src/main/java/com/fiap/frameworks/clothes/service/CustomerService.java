package com.fiap.frameworks.clothes.service;

import com.fiap.frameworks.clothes.entity.CustomerEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void save(CustomerEntity customer) {
        repository.save(customer);
    }

    public List<CustomerEntity> findAll() {
        return repository.findAll();
    }

    public CustomerEntity findById(final Long id) throws APIException {
        return repository.findById(id).orElseThrow(
                () -> new APIException(HttpStatus.NO_CONTENT));
    }

}
