package org.tracker.common;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.tracker.common.domain.TrackerDTO;
import org.tracker.common.service.TrackerService;
import org.tracker.expense.domain.ExpenseDTO;
import org.tracker.expense.service.ExpenseService;
import org.tracker.income.domain.IncomeDTO;
import org.tracker.income.service.IncomeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TrackerServiceTests {

    @InjectMocks
    private TrackerService service;

    @Mock
    private ExpenseService expenseService;

    @Mock
    private IncomeService incomeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRecordsSortedByDate() {
        ExpenseDTO expense = new ExpenseDTO();
        expense.setDate(LocalDate.of(2022, 1, 1));
        when(expenseService.searchList()).thenReturn(Arrays.asList(expense));

        IncomeDTO income = new IncomeDTO();
        income.setDate(LocalDate.of(2021, 1, 1));
        when(incomeService.searchList()).thenReturn(Arrays.asList(income));

        List<TrackerDTO> records = service.getAllListByDate();

        assertEquals(2, records.size());
        assertEquals(expense, records.get(0));
        assertEquals(income, records.get(1));
    }

}
