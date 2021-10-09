package com.quanly.demo.model.dto;

import com.quanly.demo.model.CustomerRoom;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoomDto {
    private int roomId;
    private String name;
    private boolean status;
    private String code;

    private List<CustomerRoom> listCustomerRoom = new ArrayList<CustomerRoom>();
}
