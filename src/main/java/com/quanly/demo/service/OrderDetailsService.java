package com.quanly.demo.service;

import com.quanly.demo.model.OrderDetails;
import com.quanly.demo.model.RegimenDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsService extends JpaRepository<OrderDetails, Integer> {
    //List<OrderDetails> findByOrderDetailsRegimenDetails(RegimenDetails regimenDetails);
    OrderDetails findByOrderDetailsRegimenDetails(RegimenDetails regimenDetails);
}
