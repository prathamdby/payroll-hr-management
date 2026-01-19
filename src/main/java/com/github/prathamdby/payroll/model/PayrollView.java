package com.github.prathamdby.payroll.model;

public class PayrollView {
  private final int payrollId;
  private final int employeeId;
  private final String salaryAmount;
  private final String bankAccountMasked;
  private final String taxId;
  private final String paymentDate;

  public PayrollView(
      int payrollId,
      int employeeId,
      String salaryAmount,
      String bankAccountMasked,
      String taxId,
      String paymentDate) {
    this.payrollId = payrollId;
    this.employeeId = employeeId;
    this.salaryAmount = salaryAmount;
    this.bankAccountMasked = bankAccountMasked;
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

  public String getBankAccountMasked() {
    return bankAccountMasked;
  }

  public String getTaxId() {
    return taxId;
  }

  public String getPaymentDate() {
    return paymentDate;
  }
}
