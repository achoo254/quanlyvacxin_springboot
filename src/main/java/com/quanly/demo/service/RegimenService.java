package com.quanly.demo.service;

import com.quanly.demo.model.Regimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimenService extends JpaRepository<Regimen, Integer> {
    //todo
}
