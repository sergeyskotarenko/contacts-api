package com.github.sergeyskotarenko.contacts.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
}
