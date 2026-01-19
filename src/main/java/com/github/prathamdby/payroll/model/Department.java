package com.github.prathamdby.payroll.model;

public class Department {
  private final int departmentId;
  private final String departmentName;
  private final Integer managerId;

  public Department(int departmentId, String departmentName, Integer managerId) {
    this.departmentId = departmentId;
    this.departmentName = departmentName;
    this.managerId = managerId;
  }

  public int getDepartmentId() {
    return departmentId;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public Integer getManagerId() {
    return managerId;
  }
}
