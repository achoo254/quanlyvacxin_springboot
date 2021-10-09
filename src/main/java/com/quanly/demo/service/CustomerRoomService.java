package com.quanly.demo.service;

import com.quanly.demo.model.CustomerRoom;
import com.quanly.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRoomService extends JpaRepository<CustomerRoom, Integer> {
    @Query(nativeQuery = true, value = "select ISNULL(MAX(order_by), 0) + 1 from customer_room where room_id = :room_id")
    int getOrderBy(@Param("room_id") int roomId);

    List<CustomerRoom> findByCustomerRoomRoom(Room room);

    @Query(nativeQuery = true, value = "select * from customer_room where customer_room_id = :customer_room_id and room_id = :room_id")
    CustomerRoom getOneCustomerRoomIdAndRoomId(@Param("customer_room_id") int customer_room_id, @Param("room_id") int room_id);


}
