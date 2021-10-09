package com.quanly.demo.model.dto;

import com.quanly.demo.model.OrderDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrdersDto {
    private int orderId;
    private java.util.Date datePrinted;
    private int total;
    private List<OrderDetails> listOrderDetails = new ArrayList<OrderDetails>();
}
