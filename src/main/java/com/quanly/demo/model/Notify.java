package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Notify")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notify {
    @Id
    @Column(name = "NotifyId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notifyId;

    @Column(name = "Details", columnDefinition = "NVARCHAR(255)")
    private String details;

    @Column(name = "Dates")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dates;

    @Column(name = "Status", nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "customerId")
    private Customer notifyCustomer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "roomId")
    private Room notifyRoom;
}
