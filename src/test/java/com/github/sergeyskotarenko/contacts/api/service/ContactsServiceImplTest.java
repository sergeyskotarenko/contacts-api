package com.github.sergeyskotarenko.contacts.api.service;

import com.github.sergeyskotarenko.contacts.api.dto.ContactDto;
import com.github.sergeyskotarenko.contacts.api.dto.ContactsResponseDto;
import com.github.sergeyskotarenko.contacts.api.repository.ContactsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactsServiceImplTest {

    @Autowired
    private ContactsService contactsService;

    @MockBean
    private ContactsRepository contactsRepository;

    @Test
    public void filterTest() {
        given(contactsRepository.findAllContactsAfterId(anyInt())).willReturn(Stream.of(
                new ContactDto(1, "Alice"),
                new ContactDto(2, "Bob"),
                new ContactDto(3, "Chelsie"),
                new ContactDto(4, "Don"),
                new ContactDto(5, "Elisabeth")
        ));

        ContactsResponseDto contactsByFilter = contactsService.findContactsByFilter("^A.*$", 20, 0);

        assertEquals(4, contactsByFilter.getContacts().size());
        assertFalse(contactsByFilter.getContacts().stream().anyMatch(contact -> contact.getName().startsWith("A")));
    }

    @Test
    public void emptyResponseWhenNoContactsWereFound() {
        given(contactsRepository.findAllContactsAfterId(anyInt())).willReturn(Stream.empty());

        ContactsResponseDto contactsByFilter = contactsService.findContactsByFilter("", 20, 0);

        assertTrue(contactsByFilter.getContacts().isEmpty());
        assertEquals(Integer.valueOf(-1), contactsByFilter.getLastId());
    }

}