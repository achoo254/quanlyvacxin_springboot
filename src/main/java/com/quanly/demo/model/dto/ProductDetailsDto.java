package com.quanly.demo.model.dto;

import com.quanly.demo.model.RegimenDetails;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailsDto {
    private int productDetailsId;
    private String lote;
    private Date date;
    private Float quantity;
    private ProductDto productDetailsProduct;
    private CategoriesDto productDetailsCategories;

    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();
}
