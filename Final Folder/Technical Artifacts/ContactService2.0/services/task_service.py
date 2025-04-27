from models.task import Task
from exceptions.validation_error import ValidationError
from repositories.task_repository import TaskRepository

# Handles logic for tasks using SQLite
class TaskService:
    def __init__(self):
        self.repo = TaskRepository()

    def add_task(self, task: Task):
        if self.repo.get(task.task_id):
            raise ValidationError("Task ID already exists.")
        self.repo.add(task)

    def get_task(self, task_id: str) -> Task:
        data = self.repo.get(task_id)
        if not data:
            raise ValidationError("Task not found.")
        return Task(
            task_id=data["id"],
            name=data["name"],
            description=data["description"],
            priority=data["priority"]
        )

    def delete_task(self, task_id: str):
        if not self.repo.get(task_id):
            raise ValidationError("Task not found.")
        self.repo.delete(task_id)

    def get_all_tasks(self):
        return [
            Task(
                task_id=item["id"],
                name=item["name"],
                description=item["description"],
                priority=item["priority"]
            ) for item in self.repo.get_all()
        ]


