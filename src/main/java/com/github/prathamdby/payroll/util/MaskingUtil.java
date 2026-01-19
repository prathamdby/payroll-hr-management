package com.github.prathamdby.payroll.util;

public final class MaskingUtil {
  private MaskingUtil() {
  }

  public static String maskPhone(String phone) {
    if (phone == null || phone.length() <= 4) {
      return "****";
    }
    String tail = phone.substring(phone.length() - 4);
    return "****" + tail;
  }

  public static String maskAccount(String account) {
    if (account == null || account.length() <= 4) {
      return "****";
    }
    String tail = account.substring(account.length() - 4);
    return "****" + tail;
  }

  public static String previewNotes(String notes) {
    if (notes == null) {
      return "";
    }
    if (notes.length() <= 24) {
      return notes;
    }
    return notes.substring(0, 24) + "...";
  }
}
