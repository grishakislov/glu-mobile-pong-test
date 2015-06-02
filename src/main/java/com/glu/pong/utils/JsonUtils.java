package com.glu.pong.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glu.pong.transport.OperationResult;

public class JsonUtils {

    public static String encode(OperationResult response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            return "";
        }
    }
}
