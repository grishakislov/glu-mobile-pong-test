package com.glu.pong.mybatis.mapper;

import com.glu.pong.mybatis.entity.UserEntity;
import org.apache.ibatis.annotations.*;

public interface UsersDao {

    @Select("select * from users " +
            "where user_id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "user_id")})
    UserEntity get(@Param("userId") String userId);

    @Insert("insert into users " +
            "(user_id, timestamp) " +
            "values " +
            "(#{userId}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(UserEntity entity);

}
