package com.github.prathamdby.payroll.dao;

import com.github.prathamdby.payroll.crypto.CryptoService;
import com.github.prathamdby.payroll.db.DbConnection;
import com.github.prathamdby.payroll.model.Payroll;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PayrollDao {
  public void createPayroll(
      int employeeId,
      String salaryAmount,
      String bankAccountNumber,
      String taxId,
      String paymentDate) throws Exception {
    String sql = "INSERT INTO payroll " +
        "(employee_id, salary_amount, bank_account_number, tax_id, payment_date) " +
        "VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, employeeId);
      stmt.setString(2, CryptoService.encryptSalary(salaryAmount));
      stmt.setString(3, CryptoService.encryptBankAccount(bankAccountNumber));
      stmt.setString(4, taxId);
      stmt.setString(5, paymentDate);
      stmt.executeUpdate();
    }
  }

  public List<Payroll> listPayroll() throws Exception {
    List<Payroll> payrollList = new ArrayList<>();
    String sql = "SELECT payroll_id, employee_id, salary_amount, bank_account_number, tax_id, payment_date " +
        "FROM payroll ORDER BY payroll_id";
    try (Connection conn = DbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int payrollId = rs.getInt("payroll_id");
        int employeeId = rs.getInt("employee_id");
        String salary = CryptoService.decryptSalary(rs.getString("salary_amount"));
        String bankAccount = CryptoService.decryptBankAccount(rs.getString("bank_account_number"));
        String taxId = rs.getString("tax_id");
        String paymentDate = rs.getString("payment_date");
        payrollList.add(new Payroll(payrollId, employeeId, salary, bankAccount, taxId, paymentDate));
      }
    }
    return payrollList;
  }
}
