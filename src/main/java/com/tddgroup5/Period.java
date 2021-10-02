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

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    long getOverlappingDays(Budget budget) {
        long overlappingDays;
        if (budget.getMonth().equals(YearMonth.from(getStart()))) {
            overlappingDays = DAYS.between(getStart(), budget.lastDay()) + 1;
        } else if (budget.getMonth().equals(YearMonth.from(getEnd()))) {
            overlappingDays = DAYS.between(budget.firstDay(), getEnd()) + 1;
        } else {
            overlappingDays = DAYS.between(budget.firstDay(), budget.lastDay()) + 1;
        }
        return overlappingDays;
    }
}
