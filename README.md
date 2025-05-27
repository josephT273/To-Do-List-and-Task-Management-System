# 📋 To-Do List & Task Management System

A simple and functional Java-based console application for managing daily tasks with features like adding tasks, updating deadlines, prioritizing, and generating reports. Built with Java 21 and JDBC, backed by MySQL or any JDBC-compatible database.

---

## ✨ Features

- 🔐 User registration & login system
- ➕ Add new tasks with priority and deadlines
- ✏️ Update existing task details
- ✅ Mark all tasks as completed
- 📋 View all, pending, and completed tasks
- 📊 Generate task summary reports
- 🧑 Account menu for profile and password management
- 📅 Sort tasks by deadline

---

## 🛠️ Tech Stack

- **Language:** Java 21
- **Database:** MySQL (or SQLite/PostgreSQL via JDBC)
- **Build Tool:** Maven
- **Architecture:** Clean modular package structure

---

## 📦 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.t273/
│   │       ├── abstracts/     # Base classes (Menu)
│   │       ├── database/      # DAO and DB utility
│   │       ├── menu/          # Menu UIs
│   │       ├── modal/         # Task and User models
│   │       ├── todoapp/       # Business logic
│   │       └── utils/         # Helpers (validation, etc.)
│   └── resources/             # (Optional configs)
└── test/
└── java/                  # Unit tests

````

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/josephT273/To-Do-List-and-Task-Management-System.git
cd To-Do-List-and-Task-Management-System
````

### 2. Configure Database

Create a `tasks` table using the following SQL (MySQL syntax):

```sql
CREATE TABLE tasks (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  title VARCHAR(255),
  description TEXT,
  priority INT,
  deadline DATE,
  status VARCHAR(50)
);
```

And a `users` table:

```sql
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE,
  password VARCHAR(100),
  email VARCHAR(100)
);
```

Update your database credentials in the `Database.java` file:

```java
// Example (inside com.t273.database.Database)
private static final String URL = "jdbc:mysql://localhost:3306/your_db_name";
private static final String USER = "your_db_user";
private static final String PASSWORD = "your_db_password";
```

---

## 🧪 Build and Run

### Build with Maven

```bash
mvn clean package
```

### Run the Application

```bash
java -jar target/todoapp-1.0.0.jar
// Or Use
mvn compile exec:java
```

---

## 📚 Dependencies

All dependencies are declared in `pom.xml`. Major ones include:

* MySQL JDBC Driver
* JUnit (for testing)
* Maven Shade Plugin (for building executable JAR)

---

## 🤝 Contributing

Contributions are welcome! Fork this repo and submit a pull request.

---
<!-- 
## 📄 License

This project is licensed under the [MIT License](LICENSE).

--- -->

## 🙏 Credits

Built with 💻 by `T273 Team`
Inspired by productivity apps and CLI tools.
