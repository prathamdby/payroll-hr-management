package com.github.prathamdby.payroll.web;

import com.github.prathamdby.payroll.dao.PerformanceReviewDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PerformanceReviewServlet", urlPatterns = { "/performance-review" })
public class PerformanceReviewServlet extends HttpServlet {
  private final PerformanceReviewDao reviewDao = new PerformanceReviewDao();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/performance_review.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int employeeId = Integer.parseInt(request.getParameter("employee_id"));
      int reviewerId = Integer.parseInt(request.getParameter("reviewer_id"));
      String reviewDate = request.getParameter("review_date");
      String notes = request.getParameter("confidential_notes");
      int rating = Integer.parseInt(request.getParameter("rating"));

      reviewDao.createReview(employeeId, reviewerId, reviewDate, notes, rating);
      response.sendRedirect(request.getContextPath() + "/dashboard");
    } catch (Exception ex) {
      request.setAttribute("error", "Failed to save review: " + ex.getMessage());
      RequestDispatcher dispatcher = request.getRequestDispatcher("/performance_review.jsp");
      dispatcher.forward(request, response);
    }
  }
}
