package com.github.prathamdby.payroll.dao;

import com.github.prathamdby.payroll.db.DbConnection;
import com.github.prathamdby.payroll.model.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
  public void createDepartment(String departmentName, Integer managerId) throws Exception {
    String sql = "INSERT INTO departments (department_name, manager_id) VALUES (?, ?)";
    try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, departmentName);
      if (managerId == null) {
        stmt.setNull(2, java.sql.Types.INTEGER);
      } else {
        stmt.setInt(2, managerId);
      }
      stmt.executeUpdate();
    }
  }

  public List<Department> listDepartments() throws Exception {
    List<Department> departments = new ArrayList<>();
    String sql = "SELECT department_id, department_name, manager_id FROM departments ORDER BY department_id";
    try (Connection conn = DbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int id = rs.getInt("department_id");
        String name = rs.getString("department_name");
        int managerId = rs.getInt("manager_id");
        Integer managerValue = rs.wasNull() ? null : managerId;
        departments.add(new Department(id, name, managerValue));
      }
    }
    return departments;
  }
}
