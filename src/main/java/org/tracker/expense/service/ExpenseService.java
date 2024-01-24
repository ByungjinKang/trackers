package org.tracker.expense.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.expense.domain.ExpenseDTO;
import org.tracker.expense.mapper.ExpenseMapper;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class ExpenseService {

    @Setter(onMethod_ = @Autowired)
    private ExpenseMapper mapper;

    public List<ExpenseDTO> searchList() {
        return mapper.getList();
    }

    public void add(ExpenseDTO expense) {
        log.info("addExpense: " + expense);
        mapper.insert(expense);
    }

    public ExpenseDTO get(Long id) {
        return mapper.read(id);
    }

    public boolean modify(ExpenseDTO expense) {
        return mapper.update(expense) == 1;
    }

    public boolean remove(Long id) {
        return mapper.delete(id) == 1;
    }

}
