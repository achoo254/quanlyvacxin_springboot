package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orders {
    @Id
    @Column(name = "OrderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "DatePrinted")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date datePrinted;

    @Column(name = "Total")
    private int total;

    @OneToMany(mappedBy = "orderDetailsOrder", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderDetails> listOrderDetails = new ArrayList<OrderDetails>();
}
