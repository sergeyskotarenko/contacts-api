package com.github.sergeyskotarenko.contacts.api.service;

import com.github.sergeyskotarenko.contacts.api.dto.ContactDto;
import com.github.sergeyskotarenko.contacts.api.dto.ContactsDto;
import com.github.sergeyskotarenko.contacts.api.exception.BadRequestException;
import com.github.sergeyskotarenko.contacts.api.repository.ContactsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.StringHelper.isNotEmpty;

@Service
@Log4j2
public class ContactsServiceImpl implements ContactsService {

    private final ContactsRepository contactsRepository;

    public ContactsServiceImpl(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ContactsDto findContacts(String filter) {
        if (isNotEmpty(filter)) {
            Pattern pattern = getPattern(filter);
            Set<ContactDto> contactDtos = contactsRepository.findAllContacts()
                    .filter(dto -> !pattern.matcher(dto.getName()).matches())
                    .collect(Collectors.toSet());
            return new ContactsDto(contactDtos);
        } else {
            // return all or throw an exception or add pagination and return all
        }
        return new ContactsDto(new HashSet<>());
    }

    private static Pattern getPattern(String filter) {
        try {
            return Pattern.compile(filter);
        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}
