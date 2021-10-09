package com.quanly.demo.service;

import com.quanly.demo.model.Customer;
import com.quanly.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoService extends JpaRepository<UserInfo, Integer> {
    UserInfo findTop1ByTelephone(String telephone);

    UserInfo findTop1ByEmail(String email);

    UserInfo findTop1ByToken(String token);

    UserInfo findTop1ByTelephoneAndRoles(String telephone, String roles);

    List<UserInfo> findByUserInfoId(int userInfoId);

    UserInfo findByListCustomer(Customer customer);
}
