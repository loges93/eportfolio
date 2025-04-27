from services.task_service import TaskService
from models.task import Task
from database import initialize_database

# Initialize the database and service
initialize_database()
task_service = TaskService()

def print_menu():
    print("\n--- Task Manager ---")
    print("1. Add task")
    print("2. View all tasks")
    print("3. View a task by ID")
    print("4. Delete a task")
    print("5. Exit")

def run():
    while True:
        print_menu()
        choice = input("Choose an option: ").strip()

        if choice == "1":
            task_id = input("Enter task ID: ").strip()
            name = input("Enter task name: ").strip()
            description = input("Enter description: ").strip()
            try:
                priority = int(input("Enter priority (1 = high, 5 = low): ").strip())
                task = Task(task_id=task_id, name=name, description=description, priority=priority)
                task_service.add_task(task)
                print("Task added successfully.")
            except ValueError:
                print("Priority must be a number.")
            except Exception as e:
                print(f"Error: {e}")

        elif choice == "2":
            tasks = task_service.get_all_tasks()
            for t in tasks:
                print(f"[{t.priority}] {t.task_id} - {t.name}: {t.description}")

        elif choice == "3":
            task_id = input("Enter task ID to view: ").strip()
            try:
                task = task_service.get_task(task_id)
                print(f"\nID: {task.task_id}")
                print(f"Name: {task.name}")
                print(f"Description: {task.description}")
                print(f"Priority: {task.priority}")
            except Exception as e:
                print(f"Error: {e}")

        elif choice == "4":
            task_id = input("Enter task ID to delete: ").strip()
            try:
                task_service.delete_task(task_id)
                print("Task deleted.")
            except Exception as e:
                print(f"Error: {e}")

        elif choice == "5":
            print("Goodbye!")
            break

        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    run()
