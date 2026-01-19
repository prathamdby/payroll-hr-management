CREATE DATABASE IF NOT EXISTS payroll_hr_db; USE
    payroll_hr_db;
CREATE TABLE departments(
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL,
    manager_id INT NULL
); CREATE TABLE employees(
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone_number VARCHAR(512) NOT NULL,
    date_of_birth DATE NOT NULL,
    department_id INT NULL,
    hire_date DATE NOT NULL,
    CONSTRAINT fk_employees_department FOREIGN KEY(department_id) REFERENCES departments(department_id)
); CREATE TABLE payroll(
    payroll_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    salary_amount VARCHAR(1024) NOT NULL,
    bank_account_number VARCHAR(512) NOT NULL,
    tax_id VARCHAR(100) NOT NULL,
    payment_date DATE NOT NULL,
    CONSTRAINT fk_payroll_employee FOREIGN KEY(employee_id) REFERENCES employees(employee_id)
); CREATE TABLE attendance(
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    DATE DATE NOT NULL,
    check_in_time TIME NOT NULL,
    check_out_time TIME NOT NULL,
    CONSTRAINT fk_attendance_employee FOREIGN KEY(employee_id) REFERENCES employees(employee_id)
); CREATE TABLE performance_reviews(
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    reviewer_id INT NOT NULL,
    review_date DATE NOT NULL,
    confidential_notes VARCHAR(2048) NOT NULL,
    confidential_notes_salt VARCHAR(256) NOT NULL,
    confidential_notes_hash VARCHAR(256) NOT NULL,
    rating INT NOT NULL,
    CONSTRAINT fk_reviews_employee FOREIGN KEY(employee_id) REFERENCES employees(employee_id),
    CONSTRAINT fk_reviews_reviewer FOREIGN KEY(reviewer_id) REFERENCES employees(employee_id)
);
