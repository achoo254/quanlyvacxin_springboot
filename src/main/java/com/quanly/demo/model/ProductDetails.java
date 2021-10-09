package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ProductDetails")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productDetailsId;

    @Column(name = "Lote", columnDefinition = "NVARCHAR(255)")
    private String lote;

    @Column(name = "Date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @Column(name = "Quantity")
    private Float quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "productId")
    private Product productDetailsProduct;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "categoriesId")
    private Categories productDetailsCategories;

    @OneToMany(mappedBy = "regimenDetailsProductDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();
}
