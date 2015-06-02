package com.glu.pong.service;

import com.glu.pong.transport.BusinessException;
import com.glu.pong.transport.PongResponse;
import com.glu.pong.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PingService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserHitsService userHitsService;

    @Transactional
    public PongResponse ping(String userId) throws BusinessException {
        long timestamp = TimeUtils.getUnixTime();

        usersService.create(userId);

        long hits = userHitsService.logUserHit(userId, timestamp);

        PongResponse response = new PongResponse();
        response.setUserId(userId);
        response.setHitCounter(hits);
        response.setMessage("Pong " + hits);

        return response;
    }

}
