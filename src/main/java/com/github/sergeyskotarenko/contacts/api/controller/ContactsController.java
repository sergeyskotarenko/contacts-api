package com.github.sergeyskotarenko.contacts.api.controller;

import com.github.sergeyskotarenko.contacts.api.dto.ContactsDto;
import com.github.sergeyskotarenko.contacts.api.service.ContactsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class ContactsController {

    private final ContactsService contactsService;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping("contacts")
    public ContactsDto contacts(@RequestParam("nameFilter") String filter) {
        return contactsService.findContacts(filter);
    }

}
