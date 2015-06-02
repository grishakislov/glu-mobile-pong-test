package com.glu.pong.service;

import com.glu.pong.mybatis.entity.UserEntity;
import com.glu.pong.mybatis.mapper.UsersDao;
import com.glu.pong.utils.TimeUtils;
import org.apache.mina.util.ExpiringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {

    @Autowired
    private UsersDao usersDao;

    private ExpiringMap<String, Long> savedUsers = new ExpiringMap<>();

    @Transactional
    public void create(String userId) {
        long timestamp = TimeUtils.getUnixTime();

        UserEntity user = usersDao.get(userId);

        if (user == null) {
            saveUser(userId, timestamp);
            savedUsers.getExpirer().startExpiringIfNotStarted();
        }

    }

    private synchronized void saveUser(String userId, long timestamp) {
        if (!savedUsers.containsKey(userId)) {
            UserEntity user = new UserEntity();
            user.setUserId(userId);
            user.setTimestamp(timestamp);
            usersDao.save(user);
            savedUsers.put(userId, timestamp);
        }
    }

}
