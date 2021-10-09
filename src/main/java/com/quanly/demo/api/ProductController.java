package com.quanly.demo.api;

import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.Product;
import com.quanly.demo.model.dto.ProductDto;
import com.quanly.demo.service.CategoriesService;
import com.quanly.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoriesService categoriesService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @PostMapping(value = "/post/")
    public Product postProduct(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Product> putProductDetails(@PathVariable(value = "id") int productId,
                                                     @Valid @RequestBody Product form) {
        Product product = productService.getOne(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        product.setBuy(form.getBuy());
        product.setCountry(form.getCountry());
        product.setDetails(form.getDetails());
        product.setImages(form.getImages());
        product.setName(form.getName());
        product.setSell(form.getSell());

        Product updatedproduct = productService.save(product);
        return ResponseEntity.ok(updatedproduct);
    }

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<ProductDto>> findAll() {
        List<Product> listproduct = productService.findAll();
        if (listproduct.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped
        List<ProductDto> listProductDto = mapperConvert.mapList(listproduct, ProductDto.class);

        return new ResponseEntity<List<ProductDto>>(listProductDto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ProductDto getOne(@PathVariable("id") int id) {
        Product product = productService.getOne(id);
        if (product == null) {
            ResponseEntity.notFound().build();
        }
        //Mapped
        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        return productDto;
    }
}
