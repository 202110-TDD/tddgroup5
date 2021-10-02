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
        LocalDate overlappingStart;
        LocalDate overlappingEnd;
        if (budget.getMonth().equals(YearMonth.from(start))) {
            overlappingStart = this.start;
            overlappingEnd = budget.lastDay();
//            overlappingDays = DAYS.between(overlappingStart, overlappingEnd) + 1;
        } else if (budget.getMonth().equals(YearMonth.from(end))) {
            overlappingStart = budget.firstDay();
            overlappingEnd = this.end;
//            overlappingDays = DAYS.between(overlappingStart, overlappingEnd) + 1;
        } else {
            overlappingStart = budget.firstDay();
            overlappingEnd = budget.lastDay();
//            overlappingDays = DAYS.between(overlappingStart, overlappingEnd) + 1;
        }
        overlappingDays = DAYS.between(overlappingStart, overlappingEnd) + 1;
        return overlappingDays;
    }
}
