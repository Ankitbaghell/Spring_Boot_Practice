package com.contactService.contactService.controller;

import com.contactService.contactService.entities.Contact;
import com.contactService.contactService.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/{userId}")
    public List<Contact> getContacts(@PathVariable int userId){
        return contactService.getContacts(userId);
    }
}
