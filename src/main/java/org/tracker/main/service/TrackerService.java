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

    public List<Category> findCategory(Long userId, Long typeId) {
        return mapper.findCategory(userId, typeId);
    }

    public List<Asset> findAsset(Long userId, Long typeId) {
        return mapper.findAsset(userId, typeId);
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
