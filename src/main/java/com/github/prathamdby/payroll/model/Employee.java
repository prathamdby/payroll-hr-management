package com.github.prathamdby.payroll.model;

public class Employee {
  private final int employeeId;
  private final String fullName;
  private final String email;
  private final String phoneNumber;
  private final String dateOfBirth;
  private final Integer departmentId;
  private final String hireDate;

  public Employee(
      int employeeId,
      String fullName,
      String email,
      String phoneNumber,
      String dateOfBirth,
      Integer departmentId,
      String hireDate) {
    this.employeeId = employeeId;
    this.fullName = fullName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
    this.departmentId = departmentId;
    this.hireDate = hireDate;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getFullName() {
    return fullName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public Integer getDepartmentId() {
    return departmentId;
  }

  public String getHireDate() {
    return hireDate;
  }
}
