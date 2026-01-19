package com.github.prathamdby.payroll.model;

public class Attendance {
  private final int attendanceId;
  private final int employeeId;
  private final String date;
  private final String checkInTime;
  private final String checkOutTime;

  public Attendance(
      int attendanceId,
      int employeeId,
      String date,
      String checkInTime,
      String checkOutTime) {
    this.attendanceId = attendanceId;
    this.employeeId = employeeId;
    this.date = date;
    this.checkInTime = checkInTime;
    this.checkOutTime = checkOutTime;
  }

  public int getAttendanceId() {
    return attendanceId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getDate() {
    return date;
  }

  public String getCheckInTime() {
    return checkInTime;
  }

  public String getCheckOutTime() {
    return checkOutTime;
  }
}
