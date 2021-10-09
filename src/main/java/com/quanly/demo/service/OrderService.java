package com.quanly.demo.service;

import com.quanly.demo.model.Orders;
import com.quanly.demo.model.dto.OrdersExDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderService extends JpaRepository<Orders, Integer> {
    @Query(nativeQuery = true, value = "with t as (SELECT case when month(date_printed) = 1 then sum(total) else 0 end as 'month1',\r\n"
            + "case when month(date_printed) = 2 then sum(total) else 0 end as 'month2',\r\n"
            + "case when month(date_printed) = 3 then sum(total) else 0 end as 'month3',\r\n"
            + "case when month(date_printed) = 4 then sum(total) else 0 end as 'month4',\r\n"
            + "case when month(date_printed) = 5 then sum(total) else 0 end as 'month5',\r\n"
            + "case when month(date_printed) = 6 then sum(total) else 0 end as 'month6',\r\n"
            + "case when month(date_printed) = 7 then sum(total) else 0 end as 'month7',\r\n"
            + "case when month(date_printed) = 8 then sum(total) else 0 end as 'month8',\r\n"
            + "case when month(date_printed) = 9 then sum(total) else 0 end as 'month9',\r\n"
            + "case when month(date_printed) = 10 then sum(total) else 0 end as 'month10',\r\n"
            + "case when month(date_printed) = 11 then sum(total) else 0 end as 'month11',\r\n"
            + "case when month(date_printed) = 12 then sum(total) else 0 end as 'month12'\r\n"
            + "  FROM orders where year(date_printed) = :year group by month(date_printed))\r\n"
            + "select sum(month1) as month1, sum(month2) as month2, sum(month3) as month3, sum(month4) as month4, sum(month5) as month5, sum(month6) as month6, sum(month7) as month7, sum(month8) as month8, sum(month9) as month9, sum(month10) as month10, sum(month11) as month11, sum(month12) as month12 from t")
    List<OrdersExDto> sumOrdersByMonth(@Param("year") int year);
}
