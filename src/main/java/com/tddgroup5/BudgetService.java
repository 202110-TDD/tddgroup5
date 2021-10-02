package com.tddgroup5;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {
    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            return 0;
        }
        double result = 0;
        for (Budget budget : budgetRepo.getAll()) {
            if (end.isBefore(budget.firstDay()) || start.isAfter(budget.lastDay())) {
                continue;
            }
            if (YearMonth.from(start).equals(YearMonth.from(end))) {
                if (YearMonth.from(start).equals(budget.getMonth())) {
                    long overlappingDays = DAYS.between(start, end) + 1;
                    return budget.dailyAmount() * overlappingDays;
                }
            } else {
                long overlappingDays = getOverlappingDays(new Period(start, end), budget);
                result = result + budget.dailyAmount() * overlappingDays;
            }
        }
        return result;
    }

    private long getOverlappingDays(Period period, Budget budget) {
        long overlappingDays;
        if (budget.getMonth().equals(YearMonth.from(period.getStart()))) {
            overlappingDays = DAYS.between(period.getStart(), budget.lastDay()) + 1;
        } else if (budget.getMonth().equals(YearMonth.from(period.getEnd()))) {
            overlappingDays = DAYS.between(budget.firstDay(), period.getEnd()) + 1;
        } else {
            overlappingDays = DAYS.between(budget.firstDay(), budget.lastDay()) + 1;
        }
        return overlappingDays;
    }
}
