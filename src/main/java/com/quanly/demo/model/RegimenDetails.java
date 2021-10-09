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
@Table(name = "RegimenDetails")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RegimenDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RegimenDetailsId")
    private int regimenDetailsId;

    @Column(name = "DateInject")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateInject;

    @Column(name = "Quantity")
    private Float quantity;

    @Column(name = "Inject")
    private boolean inject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "regimenId")
    private Regimen regimenDetailsRegimen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "productDetailsId")
    private ProductDetails regimenDetailsProductDetails;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "customerId")
    private Customer regimenDetailsCustomer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "userInfoId")
    private UserInfo regimenDetailsUserInfo;

    @OneToMany(mappedBy = "orderDetailsRegimenDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderDetails> listOrderDetails = new ArrayList<OrderDetails>();

}
