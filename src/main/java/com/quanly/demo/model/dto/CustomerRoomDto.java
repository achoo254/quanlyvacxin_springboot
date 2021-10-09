package com.quanly.demo.model.dto;

import lombok.Data;

@Data
public class CustomerRoomDto {
    private int customerRoomId;
    private String status;
    private int orderBy;
    private CustomerDto customerRoomCustomer;
    private RoomDto customerRoomRoom;
}
