# Time Management and Task Prioritization System

A Java GUI-based application developed for the GUVI Java Programming Project Review 1. The system helps users manage tasks, prioritize work, and track the time spent on tasks. It also provides administrative features for managing categories and prioritization rules.

## Technology Stack

- **Programming Language:** Java 17+
- **GUI Framework:** Java Swing
- **Database:** MySQL 8+
- **Database Connectivity:** JDBC
- **Build Tool:** Apache Maven
- **Database Driver:** MySQL Connector/J

## Features

- User registration and login
- Graphical user interface using Java Swing
- Task creation and management
- Task status management (Pending and Completed)
- Task prioritization
- Task timer and time tracking
- Persistent storage using MySQL
- Database access through DAO classes
- Category management
- Administrative prioritization rules
- Database connectivity and status monitoring

*Note: The listed features should match the functionality implemented in the project.*

## Rubric Coverage

The project demonstrates the following Java programming concepts:

1. **Object-Oriented Programming (OOP):** Classes, inheritance, polymorphism, interfaces, encapsulation, and exception handling.
2. **Collections and Generics:** Collections such as `List<Task>` and generic repository interfaces.
3. **Multithreading and Synchronization:** Task timer thread and synchronized time updates.
4. **Data Access Objects (DAO):** Separate database operation classes for users, tasks, categories, time logs, and prioritization rules.
5. **JDBC Database Connectivity:** Database connection management through `DatabaseConnection`.
6. **CRUD Operations:** Creating, reading, updating, and deleting database records.
7. **GUI Development:** Desktop user interface developed using Java Swing.
8. **Maven Project Management:** Dependency management and project compilation using Maven.

## Project Requirements

Ensure the following software is installed:

- Java JDK 17 or later
- Apache Maven 3.9 or later
- MySQL Server 8 or later
- MySQL Connector/J (managed through Maven)

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/priyanshudubey3008/Time-Management-and-Task-Prioritization-System.git
```

Open the downloaded project in VS Code, IntelliJ IDEA, or Eclipse.

### 2. Configure the Database

1. Ensure that MySQL Server is installed and running.
2. Configure the database connection details according to the environment variables supported by `DatabaseConnection.java`.
3. If the application supports a database password prompt, you can use that option instead.
4. The project includes SQL schema files at the following locations:

   - `src/main/resources/schema.sql`
   - `database/schema.sql`

5. If automatic schema initialization is unavailable or manual setup is required, execute `database/schema.sql` in MySQL.

**Important:** Use the actual environment variable names defined in `DatabaseConnection.java`. Do not upload database passwords or other secrets to GitHub.

### 3. Compile the Project

Open a terminal in the project root directory and run:

```bash
mvn clean compile
```

### 4. Run the Application

Run the following command from the project root:

```bash
mvn exec:java -Dexec.mainClass=com.timemanager.Main
```

This command requires the Maven Exec Plugin to be configured in `pom.xml`.

Alternatively, open the project in your Java IDE and run the `com.timemanager.Main` class.

### 5. Create an Account and Log In

1. Launch the application.
2. Select the **Create Account** option.
3. Register an account.
4. Log in using your credentials and the appropriate role.
5. Use the available features to manage tasks and track time.

## Project Structure

```text
Time-Management-and-Task-Prioritization-System/
│
├── TimeManagementSystem_Complete/
│   ├── pom.xml
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── timemanager/
│   │       │           ├── dao/
│   │       │           ├── model/
│   │       │           ├── service/
│   │       │           ├── gui/
│   │       │           └── util/
│   │       └── resources/
│   │           └── schema.sql
│   │
│   └── ...
│
├── database/
│   └── schema.sql
│
└── README.md
```

*Note: Adjust the directory tree to match the actual repository structure. If `pom.xml` is inside `TimeManagementSystem_Complete`, run Maven commands from that directory.*

## Database Access Classes

The project uses the DAO pattern to separate database operations from application logic.

- `UserDAO` — User-related database operations.
- `TaskDAO` — Task management and persistence.
- `CategoryDAO` — Category-related database operations.
- `TimeLogDAO` — Time tracking records.
- `RuleDAO` — Prioritization rule management.
- `DatabaseConnection` — JDBC database connectivity.

## Error Handling and Troubleshooting

- **Database connection error:** Ensure MySQL Server is running and the connection details are correct.
- **Access denied:** Verify the MySQL username and password.
- **Database or table missing:** Execute the provided SQL schema if required.
- **Maven command not recognized:** Install Maven and configure its `bin` directory in the system PATH, or use your IDE's Maven integration.
- **Main class not found:** Confirm that the terminal is in the correct project directory and that the package and main class names match.
- **Dependency errors:** Check the internet connection and the Maven dependencies declared in `pom.xml`.

## Learning Outcomes

This project demonstrates practical application of:

- Java object-oriented programming
- Java Swing GUI development
- Collections and generics
- Multithreading and synchronization
- JDBC and MySQL integration
- Database CRUD operations
- Maven-based project management
- Separation of concerns using DAO and service layers

## Project Information

**Project Name:** Time Management and Task Prioritization System  
**Project Type:** Java Desktop Application  
**Purpose:** GUVI Java Programming Project Review 1

GitHub Repository: [Time Management and Task Prioritization System](https://github.com/priyanshudubey3008/Time-Management-and-Task-Prioritization-System)
