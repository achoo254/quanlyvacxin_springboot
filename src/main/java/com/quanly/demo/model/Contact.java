package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Contact")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactId")
    private int contactId;

    @Column(name = "Name", columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(name = "FullName", columnDefinition = "NVARCHAR(255)")
    private String fullName;

    @Column(name = "Email", columnDefinition = "NVARCHAR(255)")
    private String email;

    @Column(name = "Telephone", columnDefinition = "NVARCHAR(255)")
    private String telephone;

    @Column(name = "Details", columnDefinition = "NTEXT")
    private String details;
}
