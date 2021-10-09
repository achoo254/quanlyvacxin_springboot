package com.quanly.demo.api;

import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.Room;
import com.quanly.demo.model.dto.RoomDto;
import com.quanly.demo.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomService roomService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<RoomDto>> listAllRoom() {
        List<Room> listRoom = roomService.findAll();
        if (listRoom.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped
        List<RoomDto> listRegimenDto = mapperConvert.mapList(listRoom, RoomDto.class);

        return new ResponseEntity<List<RoomDto>>(listRegimenDto, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/name/{name}/status/{status}")
    public ResponseEntity<List<RoomDto>> findByNameAndStatus(@PathVariable("name") String name,
                                                             @PathVariable("status") boolean status) {

        List<Room> listroom = roomService.findByNameAndStatus(name, status);
        if (listroom.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped
        List<RoomDto> listRoomDto = mapperConvert.mapList(listroom, RoomDto.class);

        Collections.sort(listRoomDto, new Comparator<RoomDto>() {

            @Override
            public int compare(RoomDto o1, RoomDto o2) {
                // TODO Auto-generated method stub
                return o1.getCode().compareTo(o2.getCode());
            }
        });

        return new ResponseEntity<List<RoomDto>>(listRoomDto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/name/{name}/status/{status}")
    public RoomDto findTop1ByNameAndStatusOrderByNEWID(@PathVariable("name") String name,
                                                       @PathVariable("status") boolean status) {

        Room room = roomService.findTop1ByNameAndStatusOrderByNEWID(name, status);
        if (room == null) {
            ResponseEntity.notFound().build();
        }
        //Mapped
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);

        return roomDto;
    }

    @GetMapping(value = "/get/{id}")
    public RoomDto getOne(@PathVariable("id") int id) {
        Room room = roomService.getOne(id);
        if (room == null) {
            ResponseEntity.notFound().build();
        }
        //Mapped
        RoomDto roomDto = modelMapper.map(room, RoomDto.class);

        return roomDto;
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Room> putRoom(@PathVariable(value = "id") int roomId, @Valid @RequestBody Room roomForm) {
        Room room = roomService.getOne(roomId);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        room.setName(roomForm.getName());
        room.setCode(roomForm.getCode());
        room.setStatus(roomForm.isStatus());

        Room updatedroom = roomService.save(room);
        return ResponseEntity.ok(updatedroom);
    }

    @PostMapping(value = "/post/")
    public Room save(@Valid @RequestBody Room room) {
        return roomService.save(room);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable(value = "id") int id) {
        boolean check;
        try {
            Room room = roomService.getOne(id);
            if (room == null) {
                check = false;
                ResponseEntity.notFound().build();
            }

            roomService.delete(room);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
}
