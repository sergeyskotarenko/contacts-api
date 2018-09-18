package com.github.sergeyskotarenko.contacts.api.controller;

import com.github.sergeyskotarenko.contacts.api.dto.ContactsResponseDto;
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
    public ContactsResponseDto getContactsByFilter(
            @RequestParam(value = "nameFilter") String filter,
            @RequestParam(value = "limit", required = false, defaultValue = "50") Integer limit,
            @RequestParam(value = "afterId", required = false, defaultValue = "0") Integer afterId) {
        return contactsService.findContactsByFilter(filter, limit, afterId);
    }

}
