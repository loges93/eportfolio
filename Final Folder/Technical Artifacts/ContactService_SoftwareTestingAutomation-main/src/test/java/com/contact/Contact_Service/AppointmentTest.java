package com.contact.Contact_Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class AppointmentTest {

    @Test
    public void testAppointmentCreation() {
        Date futureDate = new Date(System.currentTimeMillis() + 100000); // A future date
        Appointment appointment = new Appointment("12345", futureDate, "Doctor's appointment");
        assertEquals("12345", appointment.getAppointmentId());
        assertEquals(futureDate, appointment.getAppointmentDate());
        assertEquals("Doctor's appointment", appointment.getDescription());
    }

    @Test
    public void testAppointmentInvalidId() {
        final Date futureDate = new Date(System.currentTimeMillis() + 100000); // A future date

        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Appointment(null, futureDate, "Doctor's appointment");
            }
        };
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, executable1);
        assertEquals("Invalid appointment ID", thrown1.getMessage());

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Appointment("12345678901", futureDate, "Doctor's appointment");
            }
        };
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, executable2);
        assertEquals("Invalid appointment ID", thrown2.getMessage());
    }

    @Test
    public void testAppointmentInvalidDate() {
        final Date pastDate = new Date(System.currentTimeMillis() - 100000); // A past date

        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Appointment("12345", null, "Doctor's appointment");
            }
        };
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, executable1);
        assertEquals("Invalid appointment date", thrown1.getMessage());

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Appointment("12345", pastDate, "Doctor's appointment");
            }
        };
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, executable2);
        assertEquals("Invalid appointment date", thrown2.getMessage());
    }

    @Test
    public void testAppointmentInvalidDescription() {
        final Date futureDate = new Date(System.currentTimeMillis() + 100000); // A future date

        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Appointment("12345", futureDate, null);
            }
        };
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, executable1);
        assertEquals("Invalid description", thrown1.getMessage());

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                new Appointment("12345", futureDate, "This is a very long description that exceeds the fifty characters limit.");
            }
        };
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, executable2);
        assertEquals("Invalid description", thrown2.getMessage());
    }

    @Test
    public void testUpdateDescription() {
        Date futureDate = new Date(System.currentTimeMillis() + 100000); // A future date
        final Appointment appointment = new Appointment("12345", futureDate, "Doctor's appointment");
        appointment.setDescription("New description");
        assertEquals("New description", appointment.getDescription());

        Executable executable1 = new Executable() {
            @Override
            public void execute() throws Throwable {
                appointment.setDescription(null);
            }
        };
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, executable1);
        assertEquals("Invalid description", thrown1.getMessage());

        Executable executable2 = new Executable() {
            @Override
            public void execute() throws Throwable {
                appointment.setDescription("This is a very long description that exceeds the fifty characters limit.");
            }
        };
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, executable2);
        assertEquals("Invalid description", thrown2.getMessage());
    }
}