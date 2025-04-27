package com.contact.Contact_Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class AppointmentServiceTest {

    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        appointmentService = new AppointmentService();
    }

    @Test
    public void testAddAppointment() {
        Date futureDate = new Date(System.currentTimeMillis() + 100000); // A future date
        Appointment appointment = new Appointment("12345", futureDate, "Doctor's appointment");
        appointmentService.addAppointment(appointment);
        assertEquals(appointment, appointmentService.getAppointment("12345"));
    }

    @Test
    public void testAddAppointmentDuplicateId() {
        Date futureDate = new Date(System.currentTimeMillis() + 100000); // A future date
        Appointment appointment1 = new Appointment("12345", futureDate, "Doctor's appointment");
        final Appointment appointment2 = new Appointment("12345", futureDate, "Dentist appointment");
        appointmentService.addAppointment(appointment1);

        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                appointmentService.addAppointment(appointment2);
            }
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Appointment ID already exists", thrown.getMessage());
    }

    @Test
    public void testDeleteAppointment() {
        Date futureDate = new Date(System.currentTimeMillis() + 100000); // A future date
        Appointment appointment = new Appointment("12345", futureDate, "Doctor's appointment");
        appointmentService.addAppointment(appointment);
        appointmentService.deleteAppointment("12345");
        assertNull(appointmentService.getAppointment("12345"));
    }

    @Test
    public void testDeleteNonExistentAppointment() {
        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                appointmentService.deleteAppointment("99999");
            }
        };

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Appointment ID not found", thrown.getMessage());
    }
}