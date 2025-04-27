import sqlite3
from database import get_connection

# Handles database operations for appointments
class AppointmentRepository:
    def add(self, appointment):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("""
                INSERT INTO appointments (id, date, description)
                VALUES (?, ?, ?)
            """, (appointment.appointment_id, appointment.appointment_date.isoformat(), appointment.description))
            conn.commit()

    def get(self, appointment_id):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("SELECT * FROM appointments WHERE id = ?", (appointment_id,))
            row = cursor.fetchone()
            if row:
                return {
                    "id": row[0],
                    "date": row[1],
                    "description": row[2]
                }
            return None

    def delete(self, appointment_id):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("DELETE FROM appointments WHERE id = ?", (appointment_id,))
            conn.commit()

    def get_all(self):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("SELECT * FROM appointments")
            rows = cursor.fetchall()
            return [
                {"id": row[0], "date": row[1], "description": row[2]}
                for row in rows
            ]
