package com.glu.pong.transport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationResult {

    public static final int OK = 0;
    public static final int INTERNAL_ERROR = 1;
    public static final int ARGUMENT_VALIDATION_ERROR = 3;
    public static final int TYPE_MISMATCH = 4;
    public static final int UNKNOWN_METHOD = 5;
    public static final int INTERNAL_DATABASE_ERROR = 6;

    @JsonProperty
    private int code;

    @JsonProperty
    private String message;

    @JsonProperty
    private Object payload;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }


}
