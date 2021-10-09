package com.quanly.demo.service;

import com.quanly.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomService extends JpaRepository<Room, Integer> {
    @Query(nativeQuery = true, value = "SELECT TOP 1 * FROM Room where [name] = :name and [status] = :status Order By NEWID()")
    Room findTop1ByNameAndStatusOrderByNEWID(@Param("name") String name, @Param("status") boolean status);

    //@Query(nativeQuery = true, value = "select r.name, r.code from room as r inner join customer_room as cr on r.room_id = cr.room_id where r.name = :name and r.status = :status group by r.name, r.code")
    List<Room> findByNameAndStatus(@Param("name") String name, @Param("status") boolean status);
}
