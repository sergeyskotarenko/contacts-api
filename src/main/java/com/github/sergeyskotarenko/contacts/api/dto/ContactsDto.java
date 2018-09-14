package com.github.sergeyskotarenko.contacts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ContactsDto {

    private Set<ContactDto> contacts;

}
