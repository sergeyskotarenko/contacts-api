package com.github.sergeyskotarenko.contacts.api.controller;

import com.github.sergeyskotarenko.contacts.api.dto.ContactDto;
import com.github.sergeyskotarenko.contacts.api.dto.ContactsResponseDto;
import com.github.sergeyskotarenko.contacts.api.service.ContactsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactsController.class)
public class ContactsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsService contactsService;

    @Test
    public void successResponseWhenContactsFound() throws Exception {
        ContactsResponseDto responseDto = ContactsResponseDto.builder()
                .lastId(0)
                .contacts(Collections.singletonList(new ContactDto(1, "Bob")))
                .build();

        given(contactsService.findContactsByFilter(anyString(), anyInt(), anyInt())).willReturn(responseDto);
        mockMvc.perform(get("/hello/contacts").param("nameFilter", ""))
                .andExpect(status().isOk());
    }

    @Test
    public void badRequestIsReturnedWhenNameFilterIsNotPresent() throws Exception {
        given(contactsService.findContactsByFilter(anyString(), anyInt(), anyInt()))
                .willReturn(ContactsResponseDto.builder().build());
        mockMvc.perform(get("/hello/contacts")).andExpect(status().isBadRequest());
    }

}