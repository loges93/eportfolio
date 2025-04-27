# Represents an appointment with an ID, future date, and description
# Includes validation to ensure valid ID, future date, and description length

from dataclasses import dataclass, field
from exceptions import ValidationError
from datetime import datetime

@dataclass
class Appointment:
    appointment_id: str
    date: datetime
    description: str = field(default="")
    
    def __post__init__(self):
        if not self.appointment_id or len(self.appointment_id) > 10:
                    raise ValidationError("Invalid appointment ID (must be non-empty and ≤ 10 characters).")

        if not isinstance(self.appointment_date, datetime):
            raise ValidationError("Appointment date must be a datetime object.")

        if self.appointment_date < datetime.now():
            raise ValidationError("Appointment date cannot be in the past.")

        if not self.description or len(self.description) > 50:
            raise ValidationError("Invalid description (must be non-empty and ≤ 50 characters).")

    def set_description(self, new_description: str):
        if not new_description or len(new_description) > 50:
            raise ValidationError("Invalid description (must be non-empty and ≤ 50 characters).")
        self.description = new_description

    def __str__(self):
        return f"Appointment(ID={self.appointment_id}, Date={self.appointment_date}, Description={self.description})"