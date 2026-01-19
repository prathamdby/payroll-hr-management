package com.github.prathamdby.payroll.web;

import com.github.prathamdby.payroll.dao.PayrollDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PayrollServlet", urlPatterns = { "/payroll" })
public class PayrollServlet extends HttpServlet {
  private final PayrollDao payrollDao = new PayrollDao();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/payroll_entry.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int employeeId = Integer.parseInt(request.getParameter("employee_id"));
      String salaryAmount = request.getParameter("salary_amount");
      String bankAccount = request.getParameter("bank_account_number");
      String taxId = request.getParameter("tax_id");
      String paymentDate = request.getParameter("payment_date");

      payrollDao.createPayroll(employeeId, salaryAmount, bankAccount, taxId, paymentDate);
      response.sendRedirect(request.getContextPath() + "/dashboard");
    } catch (Exception ex) {
      request.setAttribute("error", "Failed to save payroll: " + ex.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher("/payroll_entry.jsp");
      dispatcher.forward(request, response);
    }
  }
}
