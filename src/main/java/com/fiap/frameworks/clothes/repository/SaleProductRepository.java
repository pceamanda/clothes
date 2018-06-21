package com.fiap.frameworks.clothes.repository;

import com.fiap.frameworks.clothes.entity.SaleProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProductEntity, Long> {
}
