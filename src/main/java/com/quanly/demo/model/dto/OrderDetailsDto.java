package com.quanly.demo.model.dto;

import lombok.Data;

@Data
public class OrderDetailsDto {
    private int orderDetailsId;
    private int quantity;
    private int price;
    private OrdersDto orderDetailsOrder;
    private RegimenDetailsDto orderDetailsRegimenDetails;
}
