# Time Management and Task Prioritization System

Java GUI project for GUVI Java Programming Project Review 1.

## Technology
- Java 17+
- Java Swing
- MySQL 8+
- JDBC / MySQL Connector/J
- Maven

## Rubric coverage
1. OOP: inheritance, polymorphism, interfaces, exception handling
2. Collections & Generics: List<Task>, generic Repository interface
3. Multithreading & Synchronization: task timer thread and synchronized time updates
4. Database operation classes: UserDAO, TaskDAO, CategoryDAO, TimeLogDAO, RuleDAO
5. JDBC database connectivity: DatabaseConnection
6. JDBC CRUD operations

## Setup
1. Create a MySQL database using `database/schema.sql`.
2. Open the project in IntelliJ IDEA / Eclipse / VS Code.
3. Update username/password in `DatabaseConnection.java`.
4. Run `Main.java`.
5. Login with:
   - Admin: admin@example.com / admin123
   - User: user@example.com / user123

## Maven
Run:
`mvn clean compile`
Then run `com.timemanager.Main`.

## Project structure
src/main/java/com/timemanager/
- Main.java
- DatabaseConnection.java
- exception/
- model/
- dao/
- service/
- gui/
- util/

database/schema.sql
