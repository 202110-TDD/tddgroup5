package com.tddgroup5;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    long getOverlappingDays(Budget budget) {
        long overlappingDays;
        if (budget.getMonth().equals(YearMonth.from(start))) {
            overlappingDays = DAYS.between(start, budget.lastDay()) + 1;
        } else if (budget.getMonth().equals(YearMonth.from(end))) {
            overlappingDays = DAYS.between(budget.firstDay(), end) + 1;
        } else {
            overlappingDays = DAYS.between(budget.firstDay(), budget.lastDay()) + 1;
        }
        return overlappingDays;
    }
}
