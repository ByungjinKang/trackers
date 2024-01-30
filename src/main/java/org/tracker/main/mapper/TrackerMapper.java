package org.tracker.main.mapper;

import org.tracker.main.domain.TrackerDTO;

import java.util.List;

public interface TrackerMapper {
    public List<TrackerDTO> getList();
    public void insert(TrackerDTO tracker);
    public TrackerDTO read(Long id);
    public int update(TrackerDTO tracker);
    public int delete(Long id);
}
