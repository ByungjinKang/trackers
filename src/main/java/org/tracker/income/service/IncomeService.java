package org.tracker.income.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.income.domain.IncomeDTO;
import org.tracker.income.mapper.IncomeMapper;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class IncomeService {

    @Setter(onMethod_ = @Autowired)
    private IncomeMapper mapper;

    public List<IncomeDTO> searchList() {
        return mapper.getList();
    }

    public void add(IncomeDTO income) {
        log.info("addIncome: " + income);
        mapper.insert(income);
    }

    public IncomeDTO get(Long id) {
        return mapper.read(id);
    }

    public boolean modify(IncomeDTO income) {
        return mapper.update(income) == 1;
    }

    public boolean remove(Long id) {
        return mapper.delete(id) == 1;
    }

}
