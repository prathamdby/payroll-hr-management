<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Dashboard</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <div class="page page--wide">
    <div class="page-header">
      <h2 class="page-title">Dashboard</h2>
      <div class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="employee">Add Employee</a>
        <a href="payroll">Add Payroll</a>
        <a href="performance-review">Add Review</a>
      </div>
    </div>

    <c:if test="${not empty error}">
      <p class="error">${error}</p>
    </c:if>

    <div class="card">
      <h3 class="section-title">Employees</h3>
      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone (masked)</th>
            <th>DOB</th>
            <th>Department</th>
            <th>Hire Date</th>
          </tr>
          <c:forEach var="emp" items="${employees}">
            <tr>
              <td>${emp.employeeId}</td>
              <td>${emp.fullName}</td>
              <td>${emp.email}</td>
              <td>${emp.phoneNumberMasked}</td>
              <td>${emp.dateOfBirth}</td>
              <td>${emp.departmentId}</td>
              <td>${emp.hireDate}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>

    <div class="card">
      <h3 class="section-title">Payroll</h3>
      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Employee</th>
            <th>Salary (decrypted)</th>
            <th>Bank Account (masked)</th>
            <th>Tax ID</th>
            <th>Payment Date</th>
          </tr>
          <c:forEach var="pay" items="${payrollList}">
            <tr>
              <td>${pay.payrollId}</td>
              <td>${pay.employeeId}</td>
              <td>${pay.salaryAmount}</td>
              <td>${pay.bankAccountMasked}</td>
              <td>${pay.taxId}</td>
              <td>${pay.paymentDate}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>

    <div class="card">
      <h3 class="section-title">Performance Reviews</h3>
      <div class="table-wrap">
        <table>
          <tr>
            <th>ID</th>
            <th>Employee</th>
            <th>Reviewer</th>
            <th>Date</th>
            <th>Notes (preview)</th>
            <th>Notes Hash</th>
            <th>Rating</th>
          </tr>
          <c:forEach var="review" items="${reviews}">
            <tr>
              <td>${review.reviewId}</td>
              <td>${review.employeeId}</td>
              <td>${review.reviewerId}</td>
              <td>${review.reviewDate}</td>
              <td>${review.confidentialNotesPreview}</td>
              <td>${review.confidentialNotesHash}</td>
              <td>${review.rating}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </div>
</body>
</html>
