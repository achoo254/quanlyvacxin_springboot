package com.quanly.demo.service;

import com.quanly.demo.model.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyService extends JpaRepository<Notify, Integer> {

}
