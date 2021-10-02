package com.tddgroup5;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
  private String yearMonth;
  private int amount;

  public Budget(String yearMonth, int amount) {
    this.yearMonth = yearMonth;
    this.amount = amount;
  }

  public String getYearMonth() {
    return yearMonth;
  }

  public int getAmount() {
    return amount;
  }

  YearMonth getMonth() {
      return YearMonth.parse(getYearMonth(), DateTimeFormatter.ofPattern("yyyyMM"));
  }

  LocalDate firstDay() {
      return getMonth().atDay(1);
  }

  LocalDate lastDay() {
      return getMonth().atEndOfMonth();
  }

  double dailyAmount() {
      return getAmount() / (double) getMonth().lengthOfMonth();
  }

  Period createPeriod() {
      return new Period(firstDay(), lastDay());
  }
}
