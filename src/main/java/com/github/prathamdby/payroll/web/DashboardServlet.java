package com.github.prathamdby.payroll.web;

import com.github.prathamdby.payroll.dao.EmployeeDao;
import com.github.prathamdby.payroll.dao.PayrollDao;
import com.github.prathamdby.payroll.dao.PerformanceReviewDao;
import com.github.prathamdby.payroll.model.Employee;
import com.github.prathamdby.payroll.model.EmployeeView;
import com.github.prathamdby.payroll.model.Payroll;
import com.github.prathamdby.payroll.model.PayrollView;
import com.github.prathamdby.payroll.model.PerformanceReview;
import com.github.prathamdby.payroll.model.PerformanceReviewView;
import com.github.prathamdby.payroll.util.MaskingUtil;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
  private final EmployeeDao employeeDao = new EmployeeDao();
  private final PayrollDao payrollDao = new PayrollDao();
  private final PerformanceReviewDao reviewDao = new PerformanceReviewDao();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      List<EmployeeView> employeeViews = new ArrayList<>();
      for (Employee employee : employeeDao.listEmployees()) {
        employeeViews.add(new EmployeeView(
            employee.getEmployeeId(),
            employee.getFullName(),
            employee.getEmail(),
            MaskingUtil.maskPhone(employee.getPhoneNumber()),
            employee.getDateOfBirth(),
            employee.getDepartmentId(),
            employee.getHireDate()));
      }

      List<PayrollView> payrollViews = new ArrayList<>();
      for (Payroll payroll : payrollDao.listPayroll()) {
        payrollViews.add(new PayrollView(
            payroll.getPayrollId(),
            payroll.getEmployeeId(),
            payroll.getSalaryAmount(),
            MaskingUtil.maskAccount(payroll.getBankAccountNumber()),
            payroll.getTaxId(),
            payroll.getPaymentDate()));
      }

      List<PerformanceReviewView> reviewViews = new ArrayList<>();
      for (PerformanceReview review : reviewDao.listReviews()) {
        reviewViews.add(new PerformanceReviewView(
            review.getReviewId(),
            review.getEmployeeId(),
            review.getReviewerId(),
            review.getReviewDate(),
            MaskingUtil.previewNotes(review.getConfidentialNotes()),
            review.getConfidentialNotesHash(),
            review.getRating()));
      }

      request.setAttribute("employees", employeeViews);
      request.setAttribute("payrollList", payrollViews);
      request.setAttribute("reviews", reviewViews);

      RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
      dispatcher.forward(request, response);
    } catch (Exception ex) {
      request.setAttribute("error", "Failed to load dashboard: " + ex.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
      dispatcher.forward(request, response);
    }
  }
}
