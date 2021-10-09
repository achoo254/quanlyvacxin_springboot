package com.quanly.demo.model.dto;

import com.quanly.demo.model.Customer;
import com.quanly.demo.model.RegimenDetails;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserInfoDto {
    private int userInfoId;
    private String fullName;
    private String address;
    private int age;
    private String email;
    private String telephone;
    private Date birthday;
    private String roles;

    private List<Customer> listCustomer = new ArrayList<Customer>();
    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();
}
