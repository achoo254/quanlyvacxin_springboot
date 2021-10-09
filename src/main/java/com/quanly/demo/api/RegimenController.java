package com.quanly.demo.api;

import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.Regimen;
import com.quanly.demo.model.dto.RegimenDto;
import com.quanly.demo.service.RegimenService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/regimen")
public class RegimenController {
    @Autowired
    RegimenService regimenService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<RegimenDto>> listAllRegimen() {
        List<Regimen> listRegimen = regimenService.findAll();
        if (listRegimen.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped
        List<RegimenDto> listRegimenDto = mapperConvert.mapList(listRegimen, RegimenDto.class);

        return new ResponseEntity<List<RegimenDto>>(listRegimenDto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public RegimenDto getOne(@PathVariable("id") int id) {
        Regimen regimen = regimenService.getOne(id);
        if (regimen == null) {
            ResponseEntity.notFound().build();
        }
        //Mapped
        RegimenDto roomDto = modelMapper.map(regimen, RegimenDto.class);

        return roomDto;
    }


}
