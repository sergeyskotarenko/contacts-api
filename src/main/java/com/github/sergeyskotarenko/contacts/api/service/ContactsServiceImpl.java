package com.github.sergeyskotarenko.contacts.api.service;

import com.github.sergeyskotarenko.contacts.api.dto.ContactDto;
import com.github.sergeyskotarenko.contacts.api.dto.ContactsResponseDto;
import com.github.sergeyskotarenko.contacts.api.repository.ContactsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ContactsServiceImpl implements ContactsService {

    private final ContactsRepository contactsRepository;

    public ContactsServiceImpl(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable("contacts")
    public ContactsResponseDto findContactsByFilter(String filter, Integer limit, Integer afterId) {
        final Pattern pattern = Pattern.compile(filter);
        List<ContactDto> contactDtos = contactsRepository.findAllContactsAfterId(afterId)
                .filter(dto -> !pattern.matcher(dto.getName()).matches())
                .limit(limit)
                .collect(Collectors.toList());
        int lastId = contactDtos.isEmpty() ? 0 : contactDtos.get(contactDtos.size() - 1).getId();
        return ContactsResponseDto.builder()
                .contacts(contactDtos)
                .lastId(lastId)
                .build();
    }

}
