package com.contact.Contact_Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    public void testContactCreation() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testContactInvalidId() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact(null, "John", "Doe", "1234567890", "123 Main St");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345678901", "John", "Doe", "1234567890", "123 Main St");
            }
        });
    }

    @Test
    public void testContactInvalidFirstName() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", null, "Doe", "1234567890", "123 Main St");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "Johnathonnn", "Doe", "1234567890", "123 Main St");
            }
        });
    }

    @Test
    public void testContactInvalidLastName() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", null, "1234567890", "123 Main St");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", "DoeLastName", "1234567890", "123 Main St");
            }
        });
    }

    @Test
    public void testContactInvalidPhone() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", "Doe", null, "123 Main St");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", "Doe", "123456789", "123 Main St");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", "Doe", "12345678901", "123 Main St");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", "Doe", "abcdefghij", "123 Main St");
            }
        });
    }

    @Test
    public void testContactInvalidAddress() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", "Doe", "1234567890", null);
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                new Contact("12345", "John", "Doe", "1234567890", "123 Main Street with a very very long address exceeding limit");
            }
        });
    }

    @Test
    public void testUpdateFirstName() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setFirstName(null);
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setFirstName("Johnathonnn");
            }
        });
    }

    @Test
    public void testUpdateLastName() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setLastName(null);
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setLastName("DoeLastName");
            }
        });
    }

    @Test
    public void testUpdatePhone() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setPhone(null);
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setPhone("123456789");
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setPhone("abcdefghij");
            }
        });
    }

    @Test
    public void testUpdateAddress() {
        final Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        contact.setAddress("456 Elm St");
        assertEquals("456 Elm St", contact.getAddress());

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setAddress(null);
            }
        });

        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                contact.setAddress("123 Main Street with a very long address exceeding limit");
            }
        });
    }
}
