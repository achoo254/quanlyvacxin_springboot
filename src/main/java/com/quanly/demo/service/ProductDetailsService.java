package com.quanly.demo.service;

import com.quanly.demo.model.Categories;
import com.quanly.demo.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsService extends JpaRepository<ProductDetails, Integer> {
    List<ProductDetails> findByProductDetailsCategories(Categories categories);
}
