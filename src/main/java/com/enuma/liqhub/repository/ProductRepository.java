package com.enuma.liqhub.repository;

import com.enuma.liqhub.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
