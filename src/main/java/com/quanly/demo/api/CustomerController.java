package com.quanly.demo.api;


import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.Customer;
import com.quanly.demo.model.CustomerRoom;
import com.quanly.demo.model.UserInfo;
import com.quanly.demo.model.dto.CustomerDto;
import com.quanly.demo.service.CustomerRoomService;
import com.quanly.demo.service.CustomerService;
import com.quanly.demo.service.UserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    CustomerRoomService customerRoomService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @GetMapping(value = "/get/{id}")
    public CustomerDto getOne(@PathVariable("id") int id) {
        Customer customer = customerService.getOne(id);
        CustomerDto customerDto = null;
        if (customer == null) {
            ResponseEntity.notFound().build();
        } else {
            //Mapped
            customerDto = modelMapper.map(customer, CustomerDto.class);
        }

        return customerDto;
    }

    @GetMapping(value = "/get/userInfo/{userInfoId}")
    public CustomerDto findTop1ByCustomerUserInfo(@PathVariable("userInfoId") int userInfoId) {
        UserInfo userInfo = userInfoService.getOne(userInfoId);
        Customer customer = customerService.findTop1ByCustomerUserInfo(userInfo);
        CustomerDto customerDto = null;
        if (customer == null) {
            ResponseEntity.notFound().build();
        } else {
            //Mapped
            customerDto = modelMapper.map(customer, CustomerDto.class);
        }

        return customerDto;
    }

    @GetMapping(value = "/get/listCustomerRoom/{customerRoomId}")
    public CustomerDto findByListCustomerRoom(@PathVariable("customerRoomId") int customerRoomId) {
        CustomerRoom customerRoom = customerRoomService.getOne(customerRoomId);
        Customer customer = customerService.findByListCustomerRoom(customerRoom);
        if (customer == null) {
            ResponseEntity.notFound().build();
        }
        //Mapped
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);

        return customerDto;
    }

    @PostMapping(value = "/post/")
    public Customer postCustomer(@Valid @RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Customer> putCustomer(@PathVariable(value = "id") int customerId,
                                                @Valid @RequestBody Customer customerForm) {
        Customer customer = customerService.getOne(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        customer.setContactName(customerForm.getContactName());
        customer.setContactPhone(customerForm.getContactPhone());

        Customer updatedcustomer = customerService.save(customer);
        return ResponseEntity.ok(updatedcustomer);
    }
}
