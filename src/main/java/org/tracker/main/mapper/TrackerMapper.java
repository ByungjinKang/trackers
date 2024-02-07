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

    public List<TrackerDTO> getListByDate(@Param("userId") Long userId, @Param("typeId") Long typeId, @Param("appYear") Integer appYear, @Param("appMonth") Integer appMonth);
    public List<Category> findCategoryExpense(@Param("userId") Long userId);
    public List<Asset> findAssetExpense(@Param("userId") Long userId);

    public List<Category> findCategoryIncome(@Param("userId") Long userId);
    public List<Asset> findAssetIncome(@Param("userId") Long userId);


    public int countList(Long id);
    public void insert(TrackerDTO tracker);
    public TrackerDTO read(Long id);
    public int update(TrackerDTO tracker);
    public int delete(Long id);
}
