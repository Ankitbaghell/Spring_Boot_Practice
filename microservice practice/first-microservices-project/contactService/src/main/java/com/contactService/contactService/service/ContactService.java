package com.contactService.contactService.service;

import com.contactService.contactService.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
    //Fake Service
    List<Contact> contacts = List.of(new Contact(101,"ankit@gmail.com","Ankit",1),
            new Contact(102,"sumit@gmail.com","sumit",1),
            new Contact(103,"virat@gmail.com","virat",1),
            new Contact(201,"ramesh@gmail.com","ramesh",2));

    public List<Contact> getContacts(int userId){
        List<Contact> contactList = contacts.stream().filter(contact -> contact.getUserId() == userId).collect(Collectors.toList());
        return contactList;
    }

}
