package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Room")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {
    @Id
    @Column(name = "RoomId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(name = "Name", columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(name = "Status")
    private boolean status;

    @Column(name = "Code", columnDefinition = "NVARCHAR(50)")
    private String code;

    @OneToMany(mappedBy = "customerRoomRoom", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CustomerRoom> listCustomerRoom = new ArrayList<CustomerRoom>();

    @OneToMany(mappedBy = "notifyRoom", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Notify> listNotify = new ArrayList<Notify>();
}
