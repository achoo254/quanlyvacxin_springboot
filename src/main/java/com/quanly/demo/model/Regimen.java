package com.quanly.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Regimen")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Regimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RegimenId")
    private int regimenId;

    @Column(name = "Name", columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(name = "Details", columnDefinition = "NTEXT")
    private String details;

    @OneToMany(mappedBy = "regimenDetailsRegimen", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();
}
