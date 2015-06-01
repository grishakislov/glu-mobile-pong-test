package com.glu.pong.service;

import com.glu.pong.mybatis.mapper.SystemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

    @Autowired
    private SystemDao systemDao;

    public long dbSize() {
        return systemDao.dbSize().getPgDatabaseSize();
    }


}
