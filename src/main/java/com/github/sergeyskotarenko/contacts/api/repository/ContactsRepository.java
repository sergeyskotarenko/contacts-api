package com.github.sergeyskotarenko.contacts.api.repository;

import com.github.sergeyskotarenko.contacts.api.domain.Contact;
import com.github.sergeyskotarenko.contacts.api.dto.ContactDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

@Repository
public interface ContactsRepository extends JpaRepository<Contact, Integer> {

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "50"))
    @Query("select new com.github.sergeyskotarenko.contacts.api.dto.ContactDto(c.id, c.name) from Contact c")
    Stream<ContactDto> findAllContacts();

}
