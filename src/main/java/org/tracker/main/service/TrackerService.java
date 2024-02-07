package org.tracker.main.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tracker.common.domain.Asset;
import org.tracker.common.domain.Category;
import org.tracker.main.domain.TrackerDTO;
import org.tracker.main.mapper.TrackerMapper;

import java.util.List;

@Service
public class TrackerService {

    private final TrackerMapper mapper;

    @Autowired
    public TrackerService(TrackerMapper mapper) {
        this.mapper = mapper;
    }

    public List<TrackerDTO> getList() {
        return mapper.getList();
    }

    public List<TrackerDTO> getListById(Long userId, Long typeId) {
        return mapper.getListById(userId, typeId);
    }

    public List<TrackerDTO> getListByDate(Long userId, Long typeId, Integer appYear, Integer appMonth){
        return mapper.getListByDate(userId, typeId, appYear, appMonth);
    }

    public List<Category> findCategoryExpense(Long userId) {
        return mapper.findCategoryExpense(userId);
    }
    public List<Category> findCategoryIncome(Long userId) {
        return mapper.findCategoryIncome(userId);
    }

    public List<Asset> findAssetExpense(Long userId) {
        return mapper.findAssetExpense(userId);
    }
    public List<Asset> findAssetIncome(Long userId) {
        return mapper.findAssetIncome(userId);
    }

    public int countList(Long userId) {
        return mapper.countList(userId);
    }

    public void add(TrackerDTO tracker) {
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
