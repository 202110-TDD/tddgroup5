package com.tddgroup5;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    long getOverlappingDays(Period another) {
        if (end.isBefore(another.start) || start.isAfter(another.end)) {
            return 0;
        }
        LocalDate overlappingStart = this.start.isAfter(another.start)
                ? this.start
                : another.start;
        LocalDate overlappingEnd = this.end.isBefore(another.end)
                ? this.end
                : another.end;
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }
}
