package com.glu.pong.rest.business;

import com.glu.pong.transport.OperationResult;

public abstract class AbstractBusinessController {

    protected OperationResult wrap(Object payload) {
        OperationResult response = new OperationResult();
        response.setCode(OperationResult.OK);
        response.setMessage("Ok");
        response.setPayload(payload);
        return response;
    }


}
