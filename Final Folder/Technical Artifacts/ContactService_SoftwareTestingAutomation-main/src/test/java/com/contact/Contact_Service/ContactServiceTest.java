package com.contact.Contact_Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService contactService;

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
    }

    @Test
    public void testAddContact() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        assertEquals(contact, contactService.getContact("12345"));
    }

    @Test
    public void testAddContactDuplicateId() {
        final Contact contact1 = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        final Contact contact2 = new Contact("12345", "Jane", "Smith", "0987654321", "456 Elm St");
        contactService.addContact(contact1);

        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                contactService.addContact(contact2);
            }
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Contact ID already exists", thrown.getMessage());
    }

    @Test
    public void testDeleteContact() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.deleteContact("12345");
        assertNull(contactService.getContact("12345"));
    }

    @Test
    public void testUpdateContactFirstName() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContact("12345", "Jane", null, null, null);
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testUpdateContactLastName() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContact("12345", null, "Smith", null, null);
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    public void testUpdateContactPhone() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContact("12345", null, null, "0987654321", null);
        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    public void testUpdateContactAddress() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contactService.addContact(contact);
        contactService.updateContact("12345", null, null, null, "456 Elm St");
        assertEquals("456 Elm St", contact.getAddress());
    }

    @Test
    public void testUpdateNonExistentContact() {
        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                contactService.updateContact("99999", "Jane", "Smith", "0987654321", "456 Elm St");
            }
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Contact not found", thrown.getMessage());
    }
}