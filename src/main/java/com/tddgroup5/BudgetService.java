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
        Period period = new Period(start, end);
        for (Budget budget : budgetRepo.getAll()) {
            if (YearMonth.from(start).equals(YearMonth.from(end))) {
                if (YearMonth.from(start).equals(budget.getMonth())) {
                    long overlappingDays = DAYS.between(start, end) + 1;
                    return budget.dailyAmount() * overlappingDays;
                }
            } else {
                result += budget.overlappingAmount(period);
            }
        }
        return result;
    }
}
