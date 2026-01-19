<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Performance Review</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <div class="page">
    <div class="page-header">
      <h2 class="page-title">Performance Review</h2>
      <div class="nav-links">
        <a href="index.jsp">Home</a>
        <a href="dashboard">Dashboard</a>
      </div>
    </div>
    <div class="card">
      <c:if test="${not empty error}">
        <p class="error">${error}</p>
      </c:if>
      <form method="post" action="performance-review" class="form-grid">
        <div>
          <label>Employee ID</label>
          <input type="number" name="employee_id" required />
        </div>
        <div>
          <label>Reviewer ID</label>
          <input type="number" name="reviewer_id" required />
        </div>
        <div>
          <label>Review Date</label>
          <input type="date" name="review_date" required />
        </div>
        <div>
          <label>Confidential Notes</label>
          <textarea name="confidential_notes" rows="5" required></textarea>
        </div>
        <div>
          <label>Rating (1-5)</label>
          <input type="number" name="rating" min="1" max="5" required />
        </div>
        <button type="submit">Save Review</button>
      </form>
    </div>
  </div>
</body>
</html>
