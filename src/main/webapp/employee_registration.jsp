<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Employee Registration</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <div class="page">
    <div class="page-header">
      <h2 class="page-title">Employee Registration</h2>
      <div class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="dashboard">Dashboard</a>
      </div>
    </div>
    <div class="card">
      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <form method="post" action="employee" class="form-grid">
        <div>
          <label>Full Name</label>
          <input type="text" name="full_name" required />
        </div>
        <div>
          <label>Email</label>
          <input type="email" name="email" required />
        </div>
        <div>
          <label>Phone Number</label>
          <input type="text" name="phone_number" required />
        </div>
        <div>
          <label>Date of Birth</label>
          <input type="date" name="date_of_birth" required />
        </div>
        <div>
          <label>Department ID (optional)</label>
          <input type="number" name="department_id" />
        </div>
        <div>
          <label>Hire Date</label>
          <input type="date" name="hire_date" required />
        </div>
        <button type="submit">Save Employee</button>
      </form>
    </div>
  </div>
</body>
</html>
