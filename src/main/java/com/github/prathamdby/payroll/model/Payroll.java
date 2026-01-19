package com.github.prathamdby.payroll.model;

public class Payroll {
  private final int payrollId;
  private final int employeeId;
  private final String salaryAmount;
  private final String bankAccountNumber;
  private final String taxId;
  private final String paymentDate;

  public Payroll(
      int payrollId,
      int employeeId,
      String salaryAmount,
      String bankAccountNumber,
      String taxId,
      String paymentDate) {
    this.payrollId = payrollId;
    this.employeeId = employeeId;
    this.salaryAmount = salaryAmount;
    this.bankAccountNumber = bankAccountNumber;
    this.taxId = taxId;
    this.paymentDate = paymentDate;
  }

  public int getPayrollId() {
    return payrollId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getSalaryAmount() {
    return salaryAmount;
  }

  public String getBankAccountNumber() {
    return bankAccountNumber;
  }

  public String getTaxId() {
    return taxId;
  }

  public String getPaymentDate() {
    return paymentDate;
  }
}
