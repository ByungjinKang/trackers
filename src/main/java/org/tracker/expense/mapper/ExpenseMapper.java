package org.tracker.expense.mapper;

import org.tracker.expense.domain.ExpenseDTO;

import java.util.List;

public interface ExpenseMapper {
    public List<ExpenseDTO> getList();
    public void insert(ExpenseDTO expense);
    public ExpenseDTO read(Long id);
    public int update(ExpenseDTO expense);
    public int delete(Long id);

}
