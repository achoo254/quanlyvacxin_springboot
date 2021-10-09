package com.quanly.demo.api;

import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.Customer;
import com.quanly.demo.model.RegimenDetails;
import com.quanly.demo.model.dto.RegimenDetailsDto;
import com.quanly.demo.model.dto.RegimenDetailsExDto;
import com.quanly.demo.service.CustomerService;
import com.quanly.demo.service.RegimenDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/regimenDetails")
public class RegimenDetailsController {
    @Autowired
    RegimenDetailsService regimenDetailsService;

    @Autowired
    CustomerService customerService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    //Lấy theo tháng để làm thống kê
    @GetMapping(value = "/getAll/{year}/{inject}")
    public ResponseEntity<List<RegimenDetailsExDto>> countRegimenDetailsByMonth(@PathVariable("year") int year, @PathVariable("inject") boolean inject) {
        List<RegimenDetailsExDto> listRegimenDetails = regimenDetailsService.countRegimenDetailsByMonth(year, inject);
        if (listRegimenDetails.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<RegimenDetailsExDto>>(listRegimenDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll/customerId/{customerId}")
    public ResponseEntity<List<RegimenDetailsDto>> findByCustomer(@PathVariable("customerId") int customerId) {
        Customer customer = customerService.findByCustomerId(customerId);
        if (customer == null) {
            ResponseEntity.notFound().build();
        }
        List<RegimenDetails> listRegimenDetails = regimenDetailsService.findByRegimenDetailsCustomer(customer);
        if (listRegimenDetails.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped
        RegimenDetails regimenDetails = new RegimenDetails();
        for (RegimenDetails item : listRegimenDetails) {
            regimenDetails.setRegimenDetailsCustomer(item.getRegimenDetailsCustomer());
            regimenDetails.setRegimenDetailsProductDetails(item.getRegimenDetailsProductDetails());
            regimenDetails.setRegimenDetailsRegimen(item.getRegimenDetailsRegimen());
            //Không show userInfo trong đây
        }

        List<RegimenDetailsDto> listRegimenDetailsDto = mapperConvert.mapList(listRegimenDetails, RegimenDetailsDto.class);
        for (RegimenDetailsDto regimenDetailsDto : listRegimenDetailsDto) {
            assert regimenDetailsDto.getRegimenDetailsCustomer().equals(regimenDetails.getRegimenDetailsCustomer());
            assert regimenDetailsDto.getRegimenDetailsProductDetails().equals(regimenDetails.getRegimenDetailsProductDetails());
            assert regimenDetailsDto.getRegimenDetailsRegimen().equals(regimenDetails.getRegimenDetailsRegimen());
        }

        return new ResponseEntity<List<RegimenDetailsDto>>(listRegimenDetailsDto, HttpStatus.OK);
    }

    @PostMapping(value = "/post/")
    public RegimenDetails postRegimenDetails(@Valid @RequestBody RegimenDetails regimenDetails) {
        return regimenDetailsService.save(regimenDetails);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<RegimenDetails> putRegimenDetails(@PathVariable(value = "id") int regimenDetailsId,
                                                            @Valid @RequestBody RegimenDetails form) {
        RegimenDetails regimenDetails = regimenDetailsService.getOne(regimenDetailsId);
        if (regimenDetails == null) {
            return ResponseEntity.notFound().build();
        }

        regimenDetails.setDateInject(form.getDateInject());
        regimenDetails.setQuantity(form.getQuantity());
        regimenDetails.setInject(form.isInject());
        regimenDetails.setRegimenDetailsRegimen(form.getRegimenDetailsRegimen());
        regimenDetails.setRegimenDetailsCustomer(form.getRegimenDetailsCustomer());
        regimenDetails.setRegimenDetailsProductDetails(form.getRegimenDetailsProductDetails());
        regimenDetails.setRegimenDetailsUserInfo(form.getRegimenDetailsUserInfo());

        RegimenDetails updatedregimenDetails = regimenDetailsService.save(regimenDetails);
        return ResponseEntity.ok(updatedregimenDetails);
    }
}
