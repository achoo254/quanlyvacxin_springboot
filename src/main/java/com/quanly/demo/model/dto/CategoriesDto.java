package com.quanly.demo.model.dto;

import com.quanly.demo.model.ProductDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoriesDto {
    private int categoriesId;
    private String name;
    private String details;
    private List<ProductDetails> listProductDetails = new ArrayList<ProductDetails>();
}
