package com.github.sergeyskotarenko.contacts.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    private Integer id;

    private String name;
}
