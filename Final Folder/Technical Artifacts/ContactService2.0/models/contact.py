# Represents a contact with basic personal info and phone number
# Validates field lengths and enforces a strict 10-digit phone number

from dataclasses import dataclass, field
from exceptions.validation_error import ValidationError


@dataclass
class Contact:
    contact_id: str
    first_name: str
    last_name: str
    phone: str
    address: str

    def __post_init__(self):
        if not self.contact_id or len(self.contact_id) > 10:
            raise ValidationError("Invalid contact ID (must be non-empty and ≤ 10 characters).")

        if not self.first_name or len(self.first_name) > 10:
            raise ValidationError("Invalid first name (must be non-empty and ≤ 10 characters).")

        if not self.last_name or len(self.last_name) > 10:
            raise ValidationError("Invalid last name (must be non-empty and ≤ 10 characters).")

        if not self.phone or not self.phone.isdigit() or len(self.phone) != 10:
            raise ValidationError("Invalid phone number (must be exactly 10 digits).")

        if not self.address or len(self.address) > 30:
            raise ValidationError("Invalid address (must be non-empty and ≤ 30 characters).")

    def __str__(self):
        return f"Contact(ID={self.contact_id}, Name={self.first_name} {self.last_name}, Phone={self.phone}, Address={self.address})"
