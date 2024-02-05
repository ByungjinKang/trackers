package org.tracker.main.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tracker.common.domain.Asset;
import org.tracker.common.domain.Category;
import org.tracker.main.domain.TrackerDTO;

import java.util.List;

@Mapper
public interface TrackerMapper {
    public List<TrackerDTO> getList();
    public List<TrackerDTO> getListById(@Param("userId") Long userId, @Param("typeId") Long typeId);


    public List<Category> findCategory(@Param("userId") Long userId, @Param("typeId") Long typeId);
    public List<Asset> findAsset(@Param("userId") Long userId, @Param("typeId") Long typeId);


    public int countList(Long id);
    public void insert(TrackerDTO tracker);
    public TrackerDTO read(Long id);
    public int update(TrackerDTO tracker);
    public int delete(Long id);
}
