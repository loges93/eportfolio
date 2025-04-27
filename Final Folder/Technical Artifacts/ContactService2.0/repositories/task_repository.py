from database import get_connection

# Handles database operations for tasks
class TaskRepository:
    def add(self, task):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("""
                INSERT INTO tasks (id, name, description, priority)
                VALUES (?, ?, ?, ?)
            """, (task.task_id, task.name, task.description, task.priority))
            conn.commit()

    def get(self, task_id):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("SELECT * FROM tasks WHERE id = ?", (task_id,))
            row = cursor.fetchone()
            if row:
                return {
                    "id": row[0],
                    "name": row[1],
                    "description": row[2],
                    "priority": row[3]
                }
            return None

    def delete(self, task_id):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("DELETE FROM tasks WHERE id = ?", (task_id,))
            conn.commit()

    def get_all(self):
        with get_connection() as conn:
            cursor = conn.cursor()
            cursor.execute("SELECT * FROM tasks")
            rows = cursor.fetchall()
            return [
                {
                    "id": row[0],
                    "name": row[1],
                    "description": row[2],
                    "priority": row[3]
                } for row in rows
            ]
