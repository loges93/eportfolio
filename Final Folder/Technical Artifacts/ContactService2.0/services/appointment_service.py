from datetime import datetime
from models.appointment import Appointment
from exceptions.validation_error import ValidationError
from repositories.appointment_repository import AppointmentRepository

# Handles logic for appointments, now using SQLite
class AppointmentService:
    def __init__(self):
        self.repo = AppointmentRepository()

    def add_appointment(self, appointment: Appointment):
        if self.repo.get(appointment.appointment_id):
            raise ValidationError("Appointment ID already exists.")
        self.repo.add(appointment)

    def get_appointment(self, appointment_id: str) -> Appointment:
        data = self.repo.get(appointment_id)
        if not data:
            raise ValidationError("Appointment not found.")
        return Appointment(
            appointment_id=data["id"],
            appointment_date=datetime.fromisoformat(data["date"]),
            description=data["description"]
        )

    def delete_appointment(self, appointment_id: str):
        if not self.repo.get(appointment_id):
            raise ValidationError("Appointment not found.")
        self.repo.delete(appointment_id)

    def get_all_appointments(self):
        return [
            Appointment(
                appointment_id=item["id"],
                appointment_date=datetime.fromisoformat(item["date"]),
                description=item["description"]
            ) for item in self.repo.get_all()
        ]

