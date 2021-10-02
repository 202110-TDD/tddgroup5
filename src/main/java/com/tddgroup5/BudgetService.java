package com.tddgroup5;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {
    private final BudgetRepo budgetRepo;

    public BudgetService(BudgetRepo budgetRepo) {
        this.budgetRepo = budgetRepo;
    }

    public double query(LocalDate start, LocalDate end) {
        double result = 0;
        if (start.isAfter(end)) {
            return result;
        }
        for (Budget budget : budgetRepo.getAll()) {
            YearMonth budgetYearMonth = YearMonth.parse(budget.getYearMonth(), DateTimeFormatter.ofPattern("yyyyMM"));
            if (end.isBefore(budgetYearMonth.atDay(1)) || start.isAfter(budgetYearMonth.atEndOfMonth())) {
                continue;
            }
            if (YearMonth.from(start).equals(YearMonth.from(end))) {
                if (YearMonth.from(start).equals(budgetYearMonth)) {
                    long days = DAYS.between(start, end) + 1;
                    return budget.getAmount() / budgetYearMonth.lengthOfMonth() * days;
                }
            } else {
                if (budgetYearMonth.equals(YearMonth.from(start))) {
                    int startDay = start.getDayOfMonth();
                    int lengthOfMonth = budgetYearMonth.lengthOfMonth();
                    int period = lengthOfMonth - startDay + 1;
                    result = result + budget.getAmount() / lengthOfMonth * period;
                } else if (budgetYearMonth.equals(YearMonth.from(end))) {
                    int endDay = end.getDayOfMonth();
                    int lengthOfMonth = budgetYearMonth.lengthOfMonth();
                    result = result + budget.getAmount() / lengthOfMonth * endDay;
                } else {
                    result = result + budget.getAmount();
                }
            }
        }
        return result;
    }
}
