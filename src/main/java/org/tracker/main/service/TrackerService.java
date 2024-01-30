package org.tracker.main.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.main.domain.TrackerDTO;
import org.tracker.main.mapper.TrackerMapper;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class TrackerService {

    @Setter(onMethod_ = @Autowired)
    private final TrackerMapper mapper;

    public List<TrackerDTO> getList() {
        return mapper.getList();
    }

    public void add(TrackerDTO tracker) {
        log.info("addExpense: " + tracker);
        mapper.insert(tracker);
    }

    public TrackerDTO get(Long id) {
        return mapper.read(id);
    }

    public boolean modify(TrackerDTO tracker) {
        return mapper.update(tracker) == 1;
    }

    public boolean remove(Long id) {
        return mapper.delete(id) == 1;
    }


}
