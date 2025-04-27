from models.contact import Contact
from exceptions.validation_error import ValidationError
from repositories.contact_repository import ContactRepository

# Handles logic for contacts using SQLite
class ContactService:
    def __init__(self):
        self.repo = ContactRepository()

    def add_contact(self, contact: Contact):
        if self.repo.get(contact.contact_id):
            raise ValidationError("Contact ID already exists.")
        self.repo.add(contact)

    def get_contact(self, contact_id: str) -> Contact:
        data = self.repo.get(contact_id)
        if not data:
            raise ValidationError("Contact not found.")
        return Contact(
            contact_id=data["id"],
            first_name=data["first_name"],
            last_name=data["last_name"],
            phone=data["phone"],
            address=""  # optional: you could add this if your schema includes it
        )

    def delete_contact(self, contact_id: str):
        if not self.repo.get(contact_id):
            raise ValidationError("Contact not found.")
        self.repo.delete(contact_id)

    def get_all_contacts(self):
        return [
            Contact(
                contact_id=item["id"],
                first_name=item["first_name"],
                last_name=item["last_name"],
                phone=item["phone"],
                address=""  # optional
            ) for item in self.repo.get_all()
        ]
