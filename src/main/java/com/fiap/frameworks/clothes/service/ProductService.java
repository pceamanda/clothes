package com.fiap.frameworks.clothes.service;

import com.fiap.frameworks.clothes.entity.ProductEntity;
import com.fiap.frameworks.clothes.exception.APIException;
import com.fiap.frameworks.clothes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public void save(ProductEntity product) {
        repository.save(product);
    }

    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    public ProductEntity findById(final Long id) throws APIException {
        return repository.findById(id).orElseThrow(
                () -> new APIException(HttpStatus.NO_CONTENT));
    }
    
}
