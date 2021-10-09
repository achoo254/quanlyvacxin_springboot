package com.quanly.demo.api;

import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.CustomerRoom;
import com.quanly.demo.model.Room;
import com.quanly.demo.model.UserInfo;
import com.quanly.demo.model.dto.CustomerRoomDto;
import com.quanly.demo.service.CustomerRoomService;
import com.quanly.demo.service.CustomerService;
import com.quanly.demo.service.RoomService;
import com.quanly.demo.service.UserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/customerRoom")
public class CustomerRoomController {
    @Autowired
    CustomerRoomService customerRoomService;

    @Autowired
    RoomService roomService;

    @Autowired
    CustomerService customerService;

    @Autowired
    UserInfoService userInfoService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @GetMapping(value = "/getAll/roomId/{roomId}")
    public ResponseEntity<List<CustomerRoomDto>> findByCustomerRoomRoom(@PathVariable("roomId") int roomId) {
        Room room = roomService.getOne(roomId);
        List<CustomerRoom> listCustomerRoom = customerRoomService.findByCustomerRoomRoom(room);
        if (listCustomerRoom.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped
        CustomerRoom customerRoom = new CustomerRoom();
        for (CustomerRoom item : listCustomerRoom) {
            customerRoom.setCustomerRoomCustomer(item.getCustomerRoomCustomer());
            customerRoom.getCustomerRoomCustomer().setCustomerUserInfo(item.getCustomerRoomCustomer().getCustomerUserInfo());
        }

        List<CustomerRoomDto> listCustomerRoomDto = mapperConvert.mapList(listCustomerRoom, CustomerRoomDto.class);
        for (CustomerRoomDto customerRoomDto : listCustomerRoomDto) {
            assert customerRoomDto.getCustomerRoomCustomer().equals(customerRoom.getCustomerRoomCustomer());
            assert customerRoomDto.getCustomerRoomCustomer().getCustomerUserInfo().equals(customerRoom.getCustomerRoomCustomer());
        }

        return new ResponseEntity<List<CustomerRoomDto>>(listCustomerRoomDto, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<CustomerRoomDto>> getAll() {
        List<CustomerRoom> listCustomerRoom = customerRoomService.findAll();
        if (listCustomerRoom.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        List<CustomerRoomDto> listCustomerRoomDto = mapperConvert.mapList(listCustomerRoom, CustomerRoomDto.class);

        return new ResponseEntity<List<CustomerRoomDto>>(listCustomerRoomDto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public CustomerRoomDto getOne(@PathVariable("id") int id) {
        CustomerRoom customerRoom = customerRoomService.getOne(id);
        if (customerRoom == null) {
            ResponseEntity.notFound().build();
        }
        //Mapped
        CustomerRoomDto customerRoomDto = modelMapper.map(customerRoom, CustomerRoomDto.class);

        return customerRoomDto;
    }

    @GetMapping(value = "/get/orderBy/{room_id}")
    public int getOrderBy(@PathVariable("room_id") int room_id) {
        int customerRoom = customerRoomService.getOrderBy(room_id);
        return customerRoom;
    }

    @PostMapping(value = "/post/")
    public CustomerRoom postCustomerRoom(@Valid @RequestBody CustomerRoom customerRoom) {
        return customerRoomService.save(customerRoom);
    }

    @PutMapping(value = "/put/{customerRoomId}")
    public ResponseEntity<CustomerRoom> putCustomerRoom(@PathVariable(value = "customerRoomId") int customerRoomId,
                                                        @Valid @RequestBody CustomerRoom customerRoomForm) {
        CustomerRoom customerRoom = customerRoomService.getOne(customerRoomId);

        if (customerRoom == null) {
            return ResponseEntity.notFound().build();
        }

        customerRoom.setStatus(customerRoomForm.getStatus());
        customerRoom.setOrderBy(customerRoomForm.getOrderBy());
        customerRoom.setCustomerRoomRoom(customerRoomForm.getCustomerRoomRoom());
        customerRoom.setCustomerRoomCustomer(customerRoomForm.getCustomerRoomCustomer());

        CustomerRoom updatedcustomerRoom = customerRoomService.save(customerRoom);
        return ResponseEntity.ok(updatedcustomerRoom);
    }

    //Hàm xóa tất cả khách hàng đang ở trong phòng nên cần kiểm tra người thực hiện
    @DeleteMapping(value = "/deleteAll/{token}/{telephone}")
    public boolean delete(@PathVariable(value = "token") String token, @PathVariable(value = "telephone") String telephone) {
        boolean check = false;
        try {
            UserInfo user = userInfoService.findTop1ByTelephone(telephone);
            if (user == null) {
                ResponseEntity.notFound().build();
            } else {
                check = passwordEncoder.matches(token, user.getToken());
                if (check) {
                    customerRoomService.deleteAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
}
