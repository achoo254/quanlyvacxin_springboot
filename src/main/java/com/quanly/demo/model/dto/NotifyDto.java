package com.quanly.demo.model.dto;

import lombok.Data;

@Data
public class NotifyDto {
    private int notifyId;
    private String details;
    private java.util.Date dates;
    private boolean status;
    private CustomerDto notifyCustomer;
    private RoomDto notifyRoom;
}
