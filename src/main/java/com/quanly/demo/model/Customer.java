package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @Column(name = "CustomerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "ContactName", columnDefinition = "NVARCHAR(255)")
    private String contactName;

    @Column(name = "ContactPhone", columnDefinition = "NVARCHAR(255)")
    private String contactPhone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "userInfoId")
    private UserInfo customerUserInfo;

    @OneToMany(mappedBy = "customerRoomCustomer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CustomerRoom> listCustomerRoom = new ArrayList<CustomerRoom>();

    @OneToMany(mappedBy = "notifyCustomer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Notify> listNotify = new ArrayList<Notify>();

    @OneToMany(mappedBy = "regimenDetailsCustomer", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();
}
