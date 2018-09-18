package com.github.sergeyskotarenko.contacts.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class ContactsResponseDto {

    private List<ContactDto> contacts;

    private Integer lastId;

}
