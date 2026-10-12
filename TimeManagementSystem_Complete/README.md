# Time Management and Task Prioritization System

Java GUI project for GUVI Java Programming Project Review 1.

## Technology
- Java 17+
- Java Swing
- MySQL 8+
- JDBC / MySQL Connector/J
- Maven

## Rubric Coverage and Code Mapping

1. OOP (Inheritance, Polymorphism, Interfaces, Exception Handling)
   * Inheritance: `UserFrame`, `AdminFrame`, `LoginFrame`, and `RegisterFrame` extend `JFrame`; `TimerThread` extends `Thread`.
   * Polymorphism and Generics: `PriorityService` uses a bounded generic method (`<T extends Task>`).
   * Interfaces: `Repository<T, ID>` implemented by `TaskDAO`; `Task` implements `Prioritizable`.
   * Exception Handling: Custom exceptions `DatabaseOperationException` and `ValidationException` in the `exception` package.
2. Collections & Generics
   * `List<Task>`, generic `Repository<T, ID>` interface, and the bounded generic method in `PriorityService`.
3. Multithreading & Synchronization
   * `TimerThread` extends `Thread` and runs the task timer. Its `getElapsedSeconds()` and `stopTimer()` methods are synchronized.
   * `SynchronizedTimeTracker` uses synchronized methods `addSeconds()` and `getTotalSeconds()` to manage time updates safely.
   * `UserFrame` also uses `synchronized (this)` blocks to coordinate access to shared state.
4. Database Operation Classes (DAO)
   * `UserDAO`, `TaskDAO`, `CategoryDAO`, `TimeLogDAO`, and `RuleDAO`.
5. JDBC Connectivity
   * `DatabaseConnection` for database connectivity.
6. JDBC CRUD Operations
   * `TaskDAO`: Add, view, update status, and delete tasks with time-log cleanup.
   * `UserDAO`, `CategoryDAO`, and `RuleDAO`: Database operations handled by the respective classes.

## Setup

### Requirements
- Java JDK 17 or later
- Maven 3.9+
- MySQL 8+
- MySQL Connector/J (managed through Maven)

### Database Configuration
1. Ensure MySQL Server is running.
2. Configure your MySQL connection details using the environment variables supported by `DatabaseConnection.java`, or use the application's password prompt if available.
3. The application initializes the database using `src/main/resources/schema.sql`. The `database/schema.sql` file is also provided for manual setup if required.
4. If using manual setup, execute `database/schema.sql` in MySQL.

### Run the Application
1. Open the project root folder in VS Code, IntelliJ IDEA, or Eclipse.
2. Open a terminal in the project root.
3. Compile the project:

   `mvn clean compile`

4. Run the application:

   `mvn exec:java -Dexec.mainClass=com.timemanager.Main`

5. Use the Create Account option to register an account, then log in with the appropriate role.

## Project Structure

- `src/main/java/com/timemanager/` — application source code
- `src/main/java/com/timemanager/dao/` — database access classes
- `src/main/java/com/timemanager/model/` — application models
- `src/main/java/com/timemanager/service/` — business logic
- `src/main/java/com/timemanager/gui/` — Swing user interface
- `src/main/java/com/timemanager/util/` — utility classes
- `src/main/resources/schema.sql` — schema used by database initialization
- `database/schema.sql` — SQL script for manual database setup
