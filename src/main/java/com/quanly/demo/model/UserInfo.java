package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "UserInfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserInfo {
    @Id
    @Column(name = "UserInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userInfoId;

    @Column(name = "FullName", columnDefinition = "NVARCHAR(500)")
    private String fullName;

    @Column(name = "Address", columnDefinition = "NVARCHAR(500)")
    private String address;

    @Column(name = "Age")
    private int age;

    @Column(name = "Email", columnDefinition = "NVARCHAR(255)")
    private String email;

    @Column(name = "Telephone", columnDefinition = "NVARCHAR(255)")
    private String telephone;

    @Column(name = "Birthday")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthday;

    @Column(name = "Password", columnDefinition = "NVARCHAR(255)")
    private String password;

    @Column(name = "Roles", columnDefinition = "NVARCHAR(255)")
    private String roles;

    @Column(name = "Status", columnDefinition = "BIT")
    private boolean status;

    @Column(name = "Token", columnDefinition = "NVARCHAR(255)")
    private String token;

    @OneToMany(mappedBy = "customerUserInfo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Customer> listCustomer = new ArrayList<Customer>();

    @OneToMany(mappedBy = "regimenDetailsUserInfo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();
}
