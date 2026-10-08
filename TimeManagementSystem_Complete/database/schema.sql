CREATE DATABASE IF NOT EXISTS time_management;
USE time_management;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN','USER') NOT NULL DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS prioritization_rules (
    rule_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    importance_weight INT NOT NULL DEFAULT 50,
    deadline_weight INT NOT NULL DEFAULT 50,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    task_name VARCHAR(150) NOT NULL,
    description VARCHAR(500),
    deadline DATE NOT NULL,
    importance ENUM('LOW','MEDIUM','HIGH') NOT NULL,
    category_id INT,
    status ENUM('PENDING','IN_PROGRESS','COMPLETED') NOT NULL DEFAULT 'PENDING',
    priority_score DOUBLE NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS time_logs (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    task_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME,
    duration_seconds BIGINT DEFAULT 0,
    FOREIGN KEY (task_id) REFERENCES tasks(task_id) ON DELETE CASCADE
);

INSERT IGNORE INTO users(name,email,password,role) VALUES
('System Admin','admin@example.com','admin123','ADMIN'),
('Demo User','user@example.com','user123','USER');

INSERT IGNORE INTO categories(name,description) VALUES
('Study','Academic study tasks'),
('Project','College project work'),
('Personal','Personal activities'),
('Work','Professional work');

INSERT INTO prioritization_rules(name,importance_weight,deadline_weight,active)
SELECT 'Default Rule',60,40,TRUE
WHERE NOT EXISTS (SELECT 1 FROM prioritization_rules);
