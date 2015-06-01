package com.glu.pong.service;

import com.glu.pong.mybatis.entity.UserEntity;
import com.glu.pong.mybatis.mapper.UsersDao;
import com.glu.pong.transport.BusinessException;
import com.glu.pong.transport.PongResponse;
import com.glu.pong.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class PingService {

    @Autowired
    private UsersDao userDao;

    @Autowired
    private UserHitsService userHitsService;

    @Transactional
    public PongResponse ping(String userId) throws BusinessException {
        //TODO: Update if not exists
        UserEntity user = userDao.get(userId);
        long timestamp = TimeUtils.getUnixTime();
        if (user == null) {
            user = new UserEntity();
            user.setUserId(userId);
            user.setTimestamp(timestamp);
            userDao.save(user);
        }

        long hits = userHitsService.logUserHit(userId, timestamp);

        PongResponse response = new PongResponse();
        response.setUserId(userId);
        response.setHitCounter(hits);
        response.setMessage("Pong " + hits);

        return response;
    }

}
