package com.glu.pong.utils;

import com.glu.pong.transport.BusinessException;
import com.glu.pong.transport.OperationResult;

public class ExceptionFactory {

    public static BusinessException unknownMethodExc(String method) {
        return new BusinessException(OperationResult.UNKNOWN_METHOD, "Unknown method: " + method);
    }
}
