package org.tracker.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.tracker.main.domain.TrackerDTO;
import org.apache.ibatis.annotations.Param;
import org.tracker.user.domain.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO findUserByIdAndPassword(@Param("id") String id, @Param("password") String password);

    public void findUserNum(UserDTO user);
    public void insertUser(UserDTO user);
}
