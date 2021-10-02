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
        LocalDate overlappingStart = this.start.isAfter(budget.firstDay())
                ? this.start
                : budget.firstDay();
        LocalDate overlappingEnd = this.end.isBefore(budget.lastDay())
                ? this.end
                : budget.lastDay();
        if (budget.getMonth().equals(YearMonth.from(start))) {
//            overlappingEnd = budget.lastDay();
        } else if (budget.getMonth().equals(YearMonth.from(end))) {
//            overlappingEnd = this.end;
        } else {
//            overlappingEnd = budget.lastDay();
        }
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }
}
