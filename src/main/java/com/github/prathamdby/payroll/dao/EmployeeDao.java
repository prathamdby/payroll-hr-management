package com.github.prathamdby.payroll.dao;

import com.github.prathamdby.payroll.crypto.CryptoService;
import com.github.prathamdby.payroll.db.DbConnection;
import com.github.prathamdby.payroll.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
  public void createEmployee(
      String fullName,
      String email,
      String phoneNumber,
      String dateOfBirth,
      Integer departmentId,
      String hireDate) throws Exception {
    String sql = "INSERT INTO employees " +
        "(full_name, email, phone_number, date_of_birth, department_id, hire_date) " +
        "VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, fullName);
      stmt.setString(2, email);
      stmt.setString(3, CryptoService.encryptPhone(phoneNumber));
      stmt.setString(4, dateOfBirth);
      if (departmentId == null) {
        stmt.setNull(5, java.sql.Types.INTEGER);
      } else {
        stmt.setInt(5, departmentId);
      }
      stmt.setString(6, hireDate);
      stmt.executeUpdate();
    }
  }

  public List<Employee> listEmployees() throws Exception {
    List<Employee> employees = new ArrayList<>();
    String sql = "SELECT employee_id, full_name, email, phone_number, date_of_birth, department_id, hire_date " +
        "FROM employees ORDER BY employee_id";
    try (Connection conn = DbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int id = rs.getInt("employee_id");
        String name = rs.getString("full_name");
        String email = rs.getString("email");
        String phone = CryptoService.decryptPhone(rs.getString("phone_number"));
        String dob = rs.getString("date_of_birth");
        int deptId = rs.getInt("department_id");
        Integer deptValue = rs.wasNull() ? null : deptId;
        String hireDate = rs.getString("hire_date");
        employees.add(new Employee(id, name, email, phone, dob, deptValue, hireDate));
      }
    }
    return employees;
  }
}
