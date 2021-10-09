package com.quanly.demo.model.dto;

import com.quanly.demo.model.RegimenDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegimenDto {
    private int regimenId;
    private String name;
    private String details;

    private List<RegimenDetails> listRegimenDetails = new ArrayList<RegimenDetails>();
}
