package com.github.prathamdby.payroll.model;

public class PerformanceReviewView {
    private final int reviewId;
    private final int employeeId;
    private final int reviewerId;
    private final String reviewDate;
    private final String confidentialNotesPreview;
    private final String confidentialNotesHash;
    private final int rating;

    public PerformanceReviewView(
            int reviewId,
            int employeeId,
            int reviewerId,
            String reviewDate,
            String confidentialNotesPreview,
            String confidentialNotesHash,
            int rating) {
        this.reviewId = reviewId;
        this.employeeId = employeeId;
        this.reviewerId = reviewerId;
        this.reviewDate = reviewDate;
        this.confidentialNotesPreview = confidentialNotesPreview;
        this.confidentialNotesHash = confidentialNotesHash;
        this.rating = rating;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public String getConfidentialNotesPreview() {
        return confidentialNotesPreview;
    }

    public String getConfidentialNotesHash() {
        return confidentialNotesHash;
    }

    public int getRating() {
        return rating;
    }
}
