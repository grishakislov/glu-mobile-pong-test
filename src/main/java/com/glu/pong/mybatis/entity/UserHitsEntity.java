package com.glu.pong.mybatis.entity;

public class UserHitsEntity {

    private String userId;
    private long hits;
    private long lastHitTimestamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }

    public long getLastHitTimestamp() {
        return lastHitTimestamp;
    }

    public void setLastHitTimestamp(long lastHitTimestamp) {
        this.lastHitTimestamp = lastHitTimestamp;
    }
}
