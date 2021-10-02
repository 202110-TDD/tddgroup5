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
        LocalDate firstDay = budget.firstDay();
        LocalDate lastDay = budget.lastDay();
        if (end.isBefore(firstDay) || start.isAfter(lastDay)) {
            return 0;
        }
        LocalDate overlappingStart = this.start.isAfter(firstDay)
                ? this.start
                : firstDay;
        LocalDate overlappingEnd = this.end.isBefore(lastDay)
                ? this.end
                : lastDay;
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }
}
