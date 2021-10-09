package com.quanly.demo.service;

import com.quanly.demo.model.Customer;
import com.quanly.demo.model.RegimenDetails;
import com.quanly.demo.model.dto.RegimenDetailsExDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegimenDetailsService extends JpaRepository<RegimenDetails, Integer> {
    List<RegimenDetails> findByRegimenDetailsCustomer(Customer customer);

    //@Query(nativeQuery = true, value = "SELECT count(*) AS 'count', MONTH(date_inject) as 'month' FROM regimen_details where year(date_inject) = :year and inject = :inject group by month(date_inject) ")
    @Query(nativeQuery = true, value = "with t as (SELECT case when month(date_inject) = 1 then count(inject) else 0 end as 'month1',\r\n"
            + "case when month(date_inject) = 2 then count(inject) else 0 end as 'month2',\r\n"
            + "case when month(date_inject) = 3 then count(inject) else 0 end as 'month3',\r\n"
            + "case when month(date_inject) = 4 then count(inject) else 0 end as 'month4',\r\n"
            + "case when month(date_inject) = 5 then count(inject) else 0 end as 'month5',\r\n"
            + "case when month(date_inject) = 6 then count(inject) else 0 end as 'month6',\r\n"
            + "case when month(date_inject) = 7 then count(inject) else 0 end as 'month7',\r\n"
            + "case when month(date_inject) = 8 then count(inject) else 0 end as 'month8',\r\n"
            + "case when month(date_inject) = 9 then count(inject) else 0 end as 'month9',\r\n"
            + "case when month(date_inject) = 10 then count(inject) else 0 end as 'month10',\r\n"
            + "case when month(date_inject) = 11 then count(inject) else 0 end as 'month11',\r\n"
            + "case when month(date_inject) = 12 then count(inject) else 0 end as 'month12'\r\n"
            + "  FROM regimen_details where year(date_inject) = :year and inject = :inject  group by month(date_inject))\r\n"
            + "select sum(month1) as month1, sum(month2) as month2, sum(month3) as month3, sum(month4) as month4, sum(month5) as month5, sum(month6) as month6, sum(month7) as month7, sum(month8) as month8, sum(month9) as month9, sum(month10) as month10, sum(month11) as month11, sum(month12) as month12 from t")
    List<RegimenDetailsExDto> countRegimenDetailsByMonth(@Param("year") int year, @Param("inject") boolean inject);


}
