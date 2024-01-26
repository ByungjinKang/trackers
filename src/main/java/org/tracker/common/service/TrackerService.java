package org.tracker.common.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.common.domain.TrackerDTO;
import org.tracker.common.mapper.TrackerMapper;
import org.tracker.expense.domain.ExpenseDTO;
import org.tracker.expense.service.ExpenseService;
import org.tracker.income.domain.IncomeDTO;
import org.tracker.income.service.IncomeService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
@Service
@AllArgsConstructor
public class TrackerService {
   private final TrackerMapper mapper;

    public List<TrackerDTO> getAllListByDate() {
        return mapper.getAllList();
    }


}
