package com.github.prathamdby.payroll.dao;

import com.github.prathamdby.payroll.crypto.CryptoService;
import com.github.prathamdby.payroll.db.DbConnection;
import com.github.prathamdby.payroll.model.PerformanceReview;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PerformanceReviewDao {
  public void createReview(
      int employeeId,
      int reviewerId,
      String reviewDate,
      String confidentialNotes,
      int rating) throws Exception {
    String salt = CryptoService.generateNotesSalt();
    String hash = CryptoService.hashNotes(confidentialNotes, salt);
    String encryptedNotes = CryptoService.encryptNotes(confidentialNotes);
    String sql = "INSERT INTO performance_reviews " +
        "(employee_id, reviewer_id, review_date, confidential_notes, confidential_notes_salt, confidential_notes_hash, rating) "
        +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, employeeId);
      stmt.setInt(2, reviewerId);
      stmt.setString(3, reviewDate);
      stmt.setString(4, encryptedNotes);
      stmt.setString(5, salt);
      stmt.setString(6, hash);
      stmt.setInt(7, rating);
      stmt.executeUpdate();
    }
  }

  public List<PerformanceReview> listReviews() throws Exception {
    List<PerformanceReview> reviews = new ArrayList<>();
    String sql = "SELECT review_id, employee_id, reviewer_id, review_date, confidential_notes, confidential_notes_hash, rating "
        +
        "FROM performance_reviews ORDER BY review_id";
    try (Connection conn = DbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int reviewId = rs.getInt("review_id");
        int employeeId = rs.getInt("employee_id");
        int reviewerId = rs.getInt("reviewer_id");
        String reviewDate = rs.getString("review_date");
        String notes = CryptoService.decryptNotes(rs.getString("confidential_notes"));
        String notesHash = rs.getString("confidential_notes_hash");
        int rating = rs.getInt("rating");
        reviews.add(new PerformanceReview(reviewId, employeeId, reviewerId, reviewDate, notes, notesHash, rating));
      }
    }
    return reviews;
  }
}
