package com.glu.pong.mybatis.mapper;

import com.glu.pong.mybatis.entity.PgDatabaseSizeEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface SystemDao {

    @Select("select " +
            "pg_database_size('billing_mtt')")
    @Results(value = {
            @Result(property = "pgDatabaseSize", column = "pg_database_size")})
    PgDatabaseSizeEntity dbSize();

}

