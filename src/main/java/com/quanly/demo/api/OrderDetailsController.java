package com.quanly.demo.api;

import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.OrderDetails;
import com.quanly.demo.model.RegimenDetails;
import com.quanly.demo.service.OrderDetailsService;
import com.quanly.demo.service.RegimenDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/orderDetails")
public class OrderDetailsController {
    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    RegimenDetailsService regimenDetailsService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @PostMapping(value = "/post/")
    public OrderDetails postOrderDetails(@Valid @RequestBody OrderDetails orderDetails) {
        return orderDetailsService.save(orderDetails);
    }

    @GetMapping(value = "/get/regimenDetailsId/{regimenDetailsId}")
    public boolean findById(@PathVariable("regimenDetailsId") int regimenDetailsId) {
        boolean checkExisted = false;
        RegimenDetails regimenDetails = regimenDetailsService.getOne(regimenDetailsId);
        OrderDetails orderDetails = orderDetailsService.findByOrderDetailsRegimenDetails(regimenDetails);
        if (orderDetails != null) {
            checkExisted = true;
        }
        return checkExisted;
    }
}
