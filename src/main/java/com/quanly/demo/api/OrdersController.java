package com.quanly.demo.api;

import com.quanly.demo.model.Orders;
import com.quanly.demo.model.dto.OrdersExDto;
import com.quanly.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    OrderService ordersService;

    @PostMapping(value = "/post/")
    public Orders postOrders(@Valid @RequestBody Orders orders) {
        return ordersService.save(orders);
    }

    //Lấy theo tháng để làm thống kê
    @GetMapping(value = "/getAll/{year}")
    public ResponseEntity<List<OrdersExDto>> sumOrdersByMonth(@PathVariable("year") int year) {
        List<OrdersExDto> listOrders = ordersService.sumOrdersByMonth(year);
        if (listOrders.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<OrdersExDto>>(listOrders, HttpStatus.OK);
    }
}
