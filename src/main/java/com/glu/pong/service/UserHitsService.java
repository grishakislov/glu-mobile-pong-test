package com.glu.pong.service;

import com.glu.pong.mybatis.entity.UserHitsEntity;
import com.glu.pong.mybatis.mapper.UserHitsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserHitsService {

    @Autowired
    private UserHitsDao userHitsDao;

    @Transactional
    public long logUserHit(String userId, long timestamp) {
        UserHitsEntity entity = userHitsDao.get(userId);
        if (entity == null) {
            entity = new UserHitsEntity();
            entity.setUserId(userId);
            entity.setHits(1);
            entity.setLastHitTimestamp(timestamp);
            userHitsDao.save(entity);
        } else {
            userHitsDao.increment(userId, timestamp);
        }

        return entity.getHits() + 1;
    }

}
