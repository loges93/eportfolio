from database import get_connection

# Handles database operations for contacts
class ContactRepository:
    def add(self, contact):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("""
                INSERT INTO contacts (id, first_name, last_name, phone)
                VALUES (?, ?, ?, ?)
            """, (contact.contact_id, contact.first_name, contact.last_name, contact.phone))
            conn.commit()

    def get(self, contact_id):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("SELECT * FROM contacts WHERE id = ?", (contact_id,))
            row = cursor.fetchone()
            if row:
                return {
                    "id": row[0],
                    "first_name": row[1],
                    "last_name": row[2],
                    "phone": row[3]
                }
            return None

    def delete(self, contact_id):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("DELETE FROM contacts WHERE id = ?", (contact_id,))
            conn.commit()

    def get_all(self):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("SELECT * FROM contacts")
            rows = cursor.fetchall()
            return [
                {
                    "id": row[0],
                    "first_name": row[1],
                    "last_name": row[2],
                    "phone": row[3]
                } for row in rows
            ]
