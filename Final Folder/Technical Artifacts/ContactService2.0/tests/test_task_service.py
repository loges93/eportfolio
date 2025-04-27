import unittest
from services.task_service import TaskService
from models.task import Task
from exceptions.validation_error import ValidationError


class TestTaskService(unittest.TestCase):
    def setUp(self):
        self.service = TaskService()

    def test_add_and_get_task(self):
        task = Task(2, "T1", "Buy groceries", "Standard shopping")
        self.service.add_task(task)
        retrieved = self.service.get_task("T1")
        self.assertEqual(retrieved.name, "Buy groceries")

    def test_duplicate_task_id(self):
        task1 = Task(1, "T1", "Call Mom", "Daily check-in")
        task2 = Task(2, "T1", "Backup Files", "Weekly backup")
        self.service.add_task(task1)
        with self.assertRaises(ValidationError):
            self.service.add_task(task2)

    def test_task_priority_ordering(self):
        high = Task(1, "T1", "Urgent Task", "High priority")
        low = Task(5, "T2", "Chill Task", "Low priority")
        self.service.add_task(low)
        self.service.add_task(high)
        next_task = self.service.get_next_task()
        self.assertEqual(next_task.task_id, "T1")
