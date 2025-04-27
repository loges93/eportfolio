# Represents a task with an ID, name, description, and priority
# Validates all fields and ensures priority is a positive integer

from dataclasses import dataclass, field
from exceptions.validation_error import ValidationError


@dataclass(order=True)
class Task:
    priority: int                      # Lower value = higher priority
    task_id: str = field(compare=False)
    name: str = field(compare=False)
    description: str = field(compare=False, default="")

    def __post_init__(self):
        # Validate task ID
        if not self.task_id or len(self.task_id) > 10:
            raise ValidationError("Invalid task ID (must be non-empty and ≤ 10 characters).")

        # Validate task name
        if not self.name or len(self.name) > 20:
            raise ValidationError("Invalid task name (must be non-empty and ≤ 20 characters).")

        # Validate description
        if not self.description or len(self.description) > 50:
            raise ValidationError("Invalid description (must be non-empty and ≤ 50 characters).")

        # Validate priority
        if not isinstance(self.priority, int) or self.priority < 1:
            raise ValidationError("Priority must be a positive integer.")

    def set_description(self, new_description: str):
        # Update task description with validation
        if not new_description or len(new_description) > 50:
            raise ValidationError("Invalid description (must be non-empty and ≤ 50 characters).")
        self.description = new_description

    def __str__(self):
        return f"Task(ID={self.task_id}, Name={self.name}, Priority={self.priority}, Description={self.description})"
