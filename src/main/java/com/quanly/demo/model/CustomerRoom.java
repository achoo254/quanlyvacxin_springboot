package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CustomerRoom")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CustomerRoom {

    @Id
    @Column(name = "CustomerRoomId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerRoomId;

    @Column(name = "Status", columnDefinition = "NVARCHAR(255)")
    private String status;

    @Column(name = "OrderBy")
    private int orderBy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "customerId")
    private Customer customerRoomCustomer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "roomId")
    private Room customerRoomRoom;
}
