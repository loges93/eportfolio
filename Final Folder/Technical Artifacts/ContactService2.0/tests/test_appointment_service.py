import unittest
from datetime import datetime, timedelta
from services.appointment_service import AppointmentService
from models.appointment import Appointment
from exceptions.validation_error import ValidationError


class TestAppointmentService(unittest.TestCase):
    def setUp(self):
        self.service = AppointmentService()

    def test_add_and_get_appointment(self):
        appt = Appointment("A1", datetime.now() + timedelta(days=1), "Doctor Visit")
        self.service.add_appointment(appt)
        retrieved = self.service.get_appointment("A1")
        self.assertEqual(retrieved.description, "Doctor Visit")

    def test_conflict_detection(self):
        appt1 = Appointment("A1", datetime.now() + timedelta(days=1), "Meeting A")
        appt2 = Appointment("A2", appt1.appointment_date, "Meeting B")

        self.service.add_appointment(appt1)
        with self.assertRaises(ValidationError):
            self.service.add_appointment(appt2)

    def test_delete_appointment(self):
        appt = Appointment("A1", datetime.now() + timedelta(days=1), "Interview")
        self.service.add_appointment(appt)
        self.service.delete_appointment("A1")
        with self.assertRaises(ValidationError):
            self.service.get_appointment("A1")
