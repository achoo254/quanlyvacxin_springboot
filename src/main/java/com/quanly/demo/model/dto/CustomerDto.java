package com.quanly.demo.model.dto;

import com.quanly.demo.model.CustomerRoom;
import com.quanly.demo.model.Notify;
import com.quanly.demo.model.RegimenDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDto {
    private int customerId;
    private String contactName;
    private String contactPhone;
    private UserInfoDto customerUserInfo;

    private List<CustomerRoom> listCustomerRoom = new ArrayList<CustomerRoom>();

    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();

    private List<Notify> listNotify = new ArrayList<Notify>();
}
