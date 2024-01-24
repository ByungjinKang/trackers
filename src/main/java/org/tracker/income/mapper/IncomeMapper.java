package org.tracker.income.mapper;

import org.tracker.income.domain.IncomeDTO;

import java.util.List;

public interface IncomeMapper {
    public List<IncomeDTO> getList();
    public void insert(IncomeDTO income);
    public IncomeDTO read(Long id);
    public int update(IncomeDTO income);
    public int delete(Long id);

}
