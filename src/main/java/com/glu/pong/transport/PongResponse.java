package com.glu.pong.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PongResponse {

    @JsonProperty
    private String message;

    @JsonProperty
    private String userId;

    @JsonProperty
    private long hitCounter;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHitCounter(long hitCounter) {
        this.hitCounter = hitCounter;
    }
}
