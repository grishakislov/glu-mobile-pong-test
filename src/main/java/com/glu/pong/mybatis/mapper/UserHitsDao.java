package com.glu.pong.mybatis.mapper;

import com.glu.pong.mybatis.entity.UserHitsEntity;
import org.apache.ibatis.annotations.*;

public interface UserHitsDao {

    @Select("select * from user_hits " +
            "where user_id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "lastHitTimestamp", column = "last_hit_timestamp")})
    UserHitsEntity get(@Param("userId") String userId);

    @Update("update user_hits " +
            "set hits = hits + 1, " +
            "last_hit_timestamp = #{timestamp} " +
            "where user_id = #{userId}")
    void increment(@Param("userId") String userId,
                   @Param("timestamp") long timestamp);

    @Insert("insert into user_hits " +
            "(user_id, hits, last_hit_timestamp) " +
            "values " +
            "(#{userId}, #{hits}, #{lastHitTimestamp})")
    void save(UserHitsEntity entity);

}
