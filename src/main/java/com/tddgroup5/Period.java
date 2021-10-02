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
        LocalDate overlappingStart;
        LocalDate overlappingEnd;
        if (budget.getMonth().equals(YearMonth.from(start))) {
            overlappingStart = this.start;
            overlappingEnd = budget.lastDay();
        } else if (budget.getMonth().equals(YearMonth.from(end))) {
            overlappingStart = budget.firstDay();
            overlappingEnd = this.end;
        } else {
            overlappingStart = budget.firstDay();
            overlappingEnd = budget.lastDay();
        }
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }
}
