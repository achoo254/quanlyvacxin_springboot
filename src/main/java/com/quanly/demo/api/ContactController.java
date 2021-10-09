package com.quanly.demo.api;

import com.quanly.demo.model.Contact;
import com.quanly.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ContactService contactService;

    @PostMapping(value = "/post/")
    public Contact postContact(@Valid @RequestBody Contact contact) {

        SimpleMailMessage msg = new SimpleMailMessage();
        String subject = "đã gửi ý kiến đóng góp";
        String telephone = "\nSố điện thoại: ";
        String content = "\n\nNội dung ý kiến đóng góp: ";
        msg.setTo("quocdat254@gmail.com");
        msg.setSubject(contact.getFullName() + " " + subject);
        msg.setText(contact.getName() + " " + contact.getFullName() + telephone + contact.getTelephone() + content
                + contact.getDetails());

        javaMailSender.send(msg);

        return contactService.save(contact);
    }
}
