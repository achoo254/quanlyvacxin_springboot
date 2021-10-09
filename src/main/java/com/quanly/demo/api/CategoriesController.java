package com.quanly.demo.api;


import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.Categories;
import com.quanly.demo.model.dto.CategoriesDto;
import com.quanly.demo.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<CategoriesDto>> findAll() {

        List<Categories> listCategories = categoriesService.findAll();
        if (listCategories.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped

        List<CategoriesDto> listCategoriesDto = mapperConvert.mapList(listCategories, CategoriesDto.class);

        return new ResponseEntity<List<CategoriesDto>>(listCategoriesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public Categories getOne(@PathVariable("id") int id) {
        Categories categories = categoriesService.getOne(id);
        if (categories == null) {
            ResponseEntity.notFound().build();
        }
        return categories;
    }
}
