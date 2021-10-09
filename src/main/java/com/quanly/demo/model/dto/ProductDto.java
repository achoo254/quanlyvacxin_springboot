package com.quanly.demo.model.dto;

import com.quanly.demo.model.ProductDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    private int productId;

    private String images;

    private String name;

    private String details;

    private float buy;

    private float sell;

    private String country;

    private List<ProductDetails> listProductDetails = new ArrayList<ProductDetails>();

}
