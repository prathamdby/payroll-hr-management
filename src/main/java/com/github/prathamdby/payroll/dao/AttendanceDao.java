package com.github.prathamdby.payroll.dao;

import com.github.prathamdby.payroll.db.DbConnection;
import com.github.prathamdby.payroll.model.Attendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDao {
  public void createAttendance(
      int employeeId,
      String date,
      String checkInTime,
      String checkOutTime) throws Exception {
    String sql = "INSERT INTO attendance " +
        "(employee_id, date, check_in_time, check_out_time) " +
        "VALUES (?, ?, ?, ?)";
    try (Connection conn = DbConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, employeeId);
      stmt.setString(2, date);
      stmt.setString(3, checkInTime);
      stmt.setString(4, checkOutTime);
      stmt.executeUpdate();
    }
  }

  public List<Attendance> listAttendance() throws Exception {
    List<Attendance> attendanceList = new ArrayList<>();
    String sql = "SELECT attendance_id, employee_id, date, check_in_time, check_out_time " +
        "FROM attendance ORDER BY attendance_id";
    try (Connection conn = DbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int attendanceId = rs.getInt("attendance_id");
        int employeeId = rs.getInt("employee_id");
        String date = rs.getString("date");
        String checkIn = rs.getString("check_in_time");
        String checkOut = rs.getString("check_out_time");
        attendanceList.add(new Attendance(attendanceId, employeeId, date, checkIn, checkOut));
      }
    }
    return attendanceList;
  }
}
