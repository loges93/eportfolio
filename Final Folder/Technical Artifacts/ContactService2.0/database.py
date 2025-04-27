import sqlite3

# Database file name
DB_NAME = "project.db"

# Create and return a database connection
def get_connection():
    return sqlite3.connect(DB_NAME)

# Set up tables if they don't already exist
def initialize_database():
    with get_connection() as conn:
        cursor = conn.cursor()

        # Create appointments table
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS appointments (
                id TEXT PRIMARY KEY,
                date TEXT NOT NULL,
                description TEXT NOT NULL
            )
        """)

        # Create contacts table
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS contacts (
                id TEXT PRIMARY KEY,
                first_name TEXT NOT NULL,
                last_name TEXT NOT NULL,
                phone TEXT NOT NULL
            )
        """)

        # Create tasks table
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS tasks (
                id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                description TEXT NOT NULL,
                priority INTEGER NOT NULL
            )
        """)

        conn.commit()
