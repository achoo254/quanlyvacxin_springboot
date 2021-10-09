package com.quanly.demo.service;

import com.quanly.demo.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactService extends JpaRepository<Contact, Integer> {

}
