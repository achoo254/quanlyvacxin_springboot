package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "OrderDetails")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetails {
    @Id
    @Column(name = "OrderDetailsId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailsId;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "Price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "orderId")
    private Orders orderDetailsOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "regimenDetailsId")
    private RegimenDetails orderDetailsRegimenDetails;
}
