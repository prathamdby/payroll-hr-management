<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Payroll Entry</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <div class="page">
    <div class="page-header">
      <h2 class="page-title">Payroll Entry</h2>
      <div class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="dashboard">Dashboard</a>
      </div>
    </div>
    <div class="card">
      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <form method="post" action="payroll" class="form-grid">
        <div>
          <label>Employee ID</label>
          <input type="number" name="employee_id" required />
        </div>
        <div>
          <label>Salary Amount</label>
          <input type="text" name="salary_amount" required />
        </div>
        <div>
          <label>Bank Account Number</label>
          <input type="text" name="bank_account_number" required />
        </div>
        <div>
          <label>Tax ID</label>
          <input type="text" name="tax_id" required />
        </div>
        <div>
          <label>Payment Date</label>
          <input type="date" name="payment_date" required />
        </div>
        <button type="submit">Save Payroll</button>
      </form>
    </div>
  </div>
</body>
</html>
