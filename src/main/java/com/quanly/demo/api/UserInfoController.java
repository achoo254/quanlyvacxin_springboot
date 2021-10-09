package com.quanly.demo.api;


import com.quanly.demo.mapper.MapperConvert;
import com.quanly.demo.model.UserInfo;
import com.quanly.demo.model.dto.UserInfoDto;
import com.quanly.demo.service.CustomerService;
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
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    CustomerService customerSerivce;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final ModelMapper modelMapper = new ModelMapper();
    private final MapperConvert mapperConvert = new MapperConvert();

    @GetMapping(value = "/getAll/")
    public ResponseEntity<List<UserInfoDto>> listUserInfo() {
        List<UserInfo> listUserInfo = userInfoService.findAll();
        if (listUserInfo.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        //Mapped
        List<UserInfoDto> listUserInfoDto = mapperConvert.mapList(listUserInfo, UserInfoDto.class);

        return new ResponseEntity<List<UserInfoDto>>(listUserInfoDto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/telephone/{telephone}")
    public boolean findByTelephone(@PathVariable("telephone") String telephone) {
        boolean isExist;
        UserInfo userInfo = userInfoService.findTop1ByTelephone(telephone);
        if (userInfo == null) {
            isExist = false;
            ResponseEntity.notFound().build();
        } else {
            isExist = true;
        }

        return isExist;
    }

    @GetMapping(value = "/get/email/{email}")
    public boolean findByEmail(@PathVariable("email") String email) {
        boolean isExist;
        UserInfo userInfo = userInfoService.findTop1ByEmail(email);
        if (userInfo == null) {
            isExist = false;
            ResponseEntity.notFound().build();
        } else {
            isExist = true;
        }

        return isExist;
    }

    @GetMapping(value = "/get/token/{token}/{telephone}")
    public UserInfoDto findByToken(@PathVariable("token") String token, @PathVariable("telephone") String telephone) {
        boolean check;
        UserInfoDto userInfoDto = null;
        UserInfo userInfo = userInfoService.findTop1ByTelephone(telephone);

        if (userInfo == null || !userInfo.isStatus()) {
            ResponseEntity.notFound().build();
        } else {
            check = passwordEncoder.matches(token, userInfo.getToken());
            if (check) {
                //Mapped
                userInfoDto = modelMapper.map(userInfo, UserInfoDto.class);
            }
        }

        return userInfoDto;
    }

    @GetMapping(value = "/get/token/{token}/roles/{roles}/{telephone}")
    public boolean findByTokenAndRoles(@PathVariable("token") String token, @PathVariable("roles") String roles, @PathVariable("telephone") String telephone) {
        boolean isExist = false;
        boolean check;
        UserInfo userInfo = userInfoService.findTop1ByTelephoneAndRoles(telephone, roles);
        if (userInfo == null) {
            isExist = false;
            ResponseEntity.notFound().build();
        } else {
            check = passwordEncoder.matches(token, userInfo.getToken());
            if (check) {
                isExist = true;
            }
        }


        return isExist;
    }

    @GetMapping(value = "/get/{id}")
    public UserInfoDto getOne(@PathVariable("id") int id) {
        UserInfo userInfo = userInfoService.getOne(id);
        UserInfoDto userInfoDto = null;
        if (userInfo == null) {
            ResponseEntity.notFound().build();
        } else {
            //Mapped
            userInfoDto = modelMapper.map(userInfo, UserInfoDto.class);
        }
        return userInfoDto;
    }

    @PostMapping(value = "/post/")
    public UserInfo postUserInfo(@Valid @RequestBody UserInfo userInfo) {
        passwordEncoder = new BCryptPasswordEncoder();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setToken(passwordEncoder.encode(userInfo.getToken()));
        return userInfoService.save(userInfo);
    }

    @PutMapping(value = "/put/{id}")
    public ResponseEntity<UserInfo> putUserInfo(@PathVariable(value = "id") int userInfoId, @Valid @RequestBody UserInfo form) {
        UserInfo userInfo = userInfoService.getOne(userInfoId);
        if (userInfo == null) {
            return ResponseEntity.notFound().build();
        }

        userInfo.setAddress(form.getAddress());
        userInfo.setAge(form.getAge());
        userInfo.setBirthday(form.getBirthday());
        userInfo.setFullName(form.getFullName());
        userInfo.setTelephone(form.getTelephone());

        UserInfo updatedUserInfo = userInfoService.save(userInfo);
        return ResponseEntity.ok(updatedUserInfo);
    }

    @PutMapping(value = "/put/password/{id}")
    public ResponseEntity<UserInfo> putPassword(@PathVariable(value = "id") int userInfoId, @Valid @RequestBody UserInfo form) {
        UserInfo userInfo = userInfoService.getOne(userInfoId);
        if (userInfo == null) {
            return ResponseEntity.notFound().build();
        }

        userInfo.setToken(passwordEncoder.encode(form.getToken()));
        userInfo.setPassword(passwordEncoder.encode(form.getPassword()));

        UserInfo updatedUserInfo = userInfoService.save(userInfo);
        return ResponseEntity.ok(updatedUserInfo);
    }

    @PutMapping(value = "/put/roles/{id}")
    public ResponseEntity<UserInfo> putRoles(@PathVariable(value = "id") int userInfoId, @Valid @RequestBody UserInfo form) {
        UserInfo userInfo = userInfoService.getOne(userInfoId);
        if (userInfo == null) {
            return ResponseEntity.notFound().build();
        }

        userInfo.setRoles(form.getRoles());

        UserInfo updatedUserInfo = userInfoService.save(userInfo);
        return ResponseEntity.ok(updatedUserInfo);
    }

//		@RequestMapping(value = "/userInfo/{id}", method = RequestMethod.DELETE)
//		public ResponseEntity<UserInfo> deleteuserInfo(@PathVariable(value = "id") int id) {
//			UserInfo userInfo = userInfoService.getOne(id);
//			if (userInfo == null) {
//				return ResponseEntity.notFound().build();
//			}
//
//			userInfoService.delete(userInfo);
//			return ResponseEntity.ok().build();
//		}
}
