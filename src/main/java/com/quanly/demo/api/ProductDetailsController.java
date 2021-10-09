package com.quanly.demo.api;

import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.Categories;
import com.quanly.demo.model.ProductDetails;
import com.quanly.demo.model.dto.ProductDetailsDto;
import com.quanly.demo.service.CategoriesService;
import com.quanly.demo.service.ProductDetailsService;
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
@RequestMapping("/productDetails")
public class ProductDetailsController {
    @Autowired
    ProductDetailsService productDetailsService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    ProductService productService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<ProductDetailsDto>> findAll() {

        List<ProductDetails> listProductDetails = productDetailsService.findAll();
        if (listProductDetails.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Set thuộc tính Categories cho productdetails
        ProductDetails productDetails = new ProductDetails();
        for (ProductDetails item : listProductDetails) {
            productDetails.setProductDetailsCategories(item.getProductDetailsCategories());
            productDetails.setProductDetailsProduct(item.getProductDetailsProduct());
        }
        //Mapped
        List<ProductDetailsDto> listProductDetailsDto = mapperConvert.mapList(listProductDetails, ProductDetailsDto.class);
        for (ProductDetailsDto productDetailsDto : listProductDetailsDto) {
            assert productDetailsDto.getProductDetailsCategories().equals(productDetails.getProductDetailsCategories());
            assert productDetailsDto.getProductDetailsProduct().equals(productDetails.getProductDetailsProduct());
        }

        return new ResponseEntity<List<ProductDetailsDto>>(listProductDetailsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/categoriesId/{categoriesId}")
    public ResponseEntity<List<ProductDetailsDto>> findByProductDetailsCategories(@PathVariable("categoriesId") int categoriesId) {

        Categories categories = categoriesService.getOne(categoriesId);

        List<ProductDetails> listProductDetails = productDetailsService.findByProductDetailsCategories(categories);
        if (listProductDetails.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Set thuộc tính Categories cho productdetails
        ProductDetails productDetails = new ProductDetails();
        for (ProductDetails item : listProductDetails) {
            productDetails.setProductDetailsCategories(item.getProductDetailsCategories());
            productDetails.setProductDetailsProduct(item.getProductDetailsProduct());
        }
        //Mapped
        List<ProductDetailsDto> listProductDetailsDto = mapperConvert.mapList(listProductDetails, ProductDetailsDto.class);
        for (ProductDetailsDto productDetailsDto : listProductDetailsDto) {
            assert productDetailsDto.getProductDetailsCategories().equals(productDetails.getProductDetailsCategories());
            assert productDetailsDto.getProductDetailsProduct().equals(productDetails.getProductDetailsProduct());
        }

        return new ResponseEntity<List<ProductDetailsDto>>(listProductDetailsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ProductDetailsDto getOne(@PathVariable("id") int id) {
        ProductDetails productDetails = productDetailsService.getOne(id);
        if (productDetails == null) {
            ResponseEntity.notFound().build();
        }
        //Mapped
        ProductDetailsDto productDetailsDto = modelMapper.map(productDetails, ProductDetailsDto.class);

        return productDetailsDto;
    }

    @PostMapping(value = "/post/")
    public ProductDetails postProductDetails(@Valid @RequestBody ProductDetails productDetails) {
        return productDetailsService.save(productDetails);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<ProductDetails> putProductDetails(@PathVariable(value = "id") int productDetailsId,
                                                            @Valid @RequestBody ProductDetails form) {
        ProductDetails productDetails = productDetailsService.getOne(productDetailsId);
        if (productDetails == null) {
            return ResponseEntity.notFound().build();
        }

        productDetails.setDate(form.getDate());
        productDetails.setLote(form.getLote());
        productDetails.setQuantity(form.getQuantity());
        productDetails.setProductDetailsCategories(form.getProductDetailsCategories());
        productDetails.setProductDetailsProduct(form.getProductDetailsProduct());

        ProductDetails updatedcustomer = productDetailsService.save(productDetails);
        return ResponseEntity.ok(updatedcustomer);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteProductDetails(@PathVariable(value = "id") int id) {
        boolean check;
        try {
            ProductDetails productDetails = productDetailsService.getOne(id);
            if (productDetails == null) {
                check = false;
                ResponseEntity.notFound().build();
            }

            productDetailsService.delete(productDetails);
            check = true;
        } catch (Exception e) {
            check = false;
        }
        return check;
    }
}
