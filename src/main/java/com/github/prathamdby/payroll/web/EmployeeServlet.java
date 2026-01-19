package com.github.prathamdby.payroll.web;

import com.github.prathamdby.payroll.dao.EmployeeDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmployeeServlet", urlPatterns = { "/employee" })
public class EmployeeServlet extends HttpServlet {
  private final EmployeeDao employeeDao = new EmployeeDao();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_registration.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String fullName = request.getParameter("full_name");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone_number");
      String dob = request.getParameter("date_of_birth");
      String departmentRaw = request.getParameter("department_id");
      String hireDate = request.getParameter("hire_date");

      Integer departmentId = null;
      if (departmentRaw != null && !departmentRaw.trim().isEmpty()) {
        departmentId = Integer.parseInt(departmentRaw);
      }

      employeeDao.createEmployee(fullName, email, phone, dob, departmentId, hireDate);
      response.sendRedirect(request.getContextPath() + "/dashboard");
    } catch (Exception ex) {
      request.setAttribute("error", "Failed to save employee: " + ex.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher("/employee_registration.jsp");
      dispatcher.forward(request, response);
    }
  }
}
