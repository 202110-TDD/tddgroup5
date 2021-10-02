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

    public double overlappingAmount(Period period) {
        return dailyAmount() * period.getOverlappingDays(createPeriod());
    }

    private Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }

    private double dailyAmount() {
        return getAmount() / (double) getMonth().lengthOfMonth();
    }

    private LocalDate firstDay() {
        return getMonth().atDay(1);
    }

    private int getAmount() {
        return amount;
    }

    private YearMonth getMonth() {
        return YearMonth.parse(getYearMonth(), DateTimeFormatter.ofPattern("yyyyMM"));
    }

    private String getYearMonth() {
        return yearMonth;
    }

    private LocalDate lastDay() {
        return getMonth().atEndOfMonth();
    }
}
