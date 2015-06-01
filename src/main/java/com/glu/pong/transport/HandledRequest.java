package com.glu.pong.transport;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class HandledRequest {

    @NotNull
    @JsonProperty
    private String method;

    @NotNull
    @JsonProperty
    private String userId;

    public String getMethod() {
        return method;
    }

    public String getUserId() {
        return userId;
    }
}
