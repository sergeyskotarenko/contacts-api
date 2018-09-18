package com.github.sergeyskotarenko.contacts.api.service;

import com.github.sergeyskotarenko.contacts.api.dto.ContactsResponseDto;

public interface ContactsService {

    ContactsResponseDto findContactsByFilter(String filter, Integer limit, Integer afterId);

}
