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
            YearMonth budgetYearMonth = budget.getMonth();
            if (end.isBefore(budget.firstDay()) || start.isAfter(budget.lastDay())) {
                continue;
            }
            if (YearMonth.from(start).equals(YearMonth.from(end))) {
                if (YearMonth.from(start).equals(budgetYearMonth)) {
                    long overlappingDays = DAYS.between(start, end) + 1;
                    return budget.dailyAmount() * overlappingDays;
                }
            } else {
                if (budgetYearMonth.equals(YearMonth.from(start))) {
                    int startDay = start.getDayOfMonth();
                    int lengthOfMonth = budgetYearMonth.lengthOfMonth();
                    int overlappingDays = lengthOfMonth - startDay + 1;
                    result = result + budget.dailyAmount() * overlappingDays;
                } else if (budgetYearMonth.equals(YearMonth.from(end))) {
                    int overlappingDays = end.getDayOfMonth();
                    result = result + budget.dailyAmount() * overlappingDays;
                } else {
                    result = result + budget.getAmount();
                }
            }
        }
        return result;
    }
}
