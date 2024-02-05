package org.tracker.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NameToIdMapper {
    Long findIdByName(@Param("tableName") String tableName, @Param("name") String name);
}
