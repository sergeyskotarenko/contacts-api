package com.github.sergeyskotarenko.contacts.api.repository;

import com.github.sergeyskotarenko.contacts.api.domain.Contact;
import com.github.sergeyskotarenko.contacts.api.dto.ContactDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContactsRepository repository;

    @Test
    public void testQuery() {
        Contact contact1 = new Contact();
        contact1.setName("Alice");
        Contact contact2 = new Contact();
        contact2.setName("Bob");
        Contact contact3 = new Contact();
        contact3.setName("Chelsie");
        Contact contact4 = new Contact();
        contact4.setName("Don");
        Contact contact5 = new Contact();
        contact5.setName("Elisabeth");
        entityManager.persist(contact1);
        entityManager.persist(contact2);
        entityManager.persist(contact3);
        entityManager.persist(contact4);
        entityManager.persist(contact5);
        entityManager.flush();

        Supplier<Stream<ContactDto>> streamSupplier = () -> repository.findAllContactsAfterId(3);

        assertEquals(2, streamSupplier.get().count());
        assertFalse(streamSupplier.get().anyMatch(contactDto -> contactDto.getId() <= 3));
    }

}