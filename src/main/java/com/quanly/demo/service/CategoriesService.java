package com.quanly.demo.service;

import com.quanly.demo.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesService extends JpaRepository<Categories, Integer> {

}
