package com.github.sergeyskotarenko.contacts.api.service;

import com.github.sergeyskotarenko.contacts.api.dto.ContactsDto;

public interface ContactsService {

    ContactsDto findContacts(String filter);

}
