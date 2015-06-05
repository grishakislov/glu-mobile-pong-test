package com.glu.pong.rest.business;

import com.glu.pong.service.PingService;
import com.glu.pong.transport.BusinessException;
import com.glu.pong.transport.HandledRequest;
import com.glu.pong.transport.OperationResult;
import com.glu.pong.utils.ExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class HandlerController extends AbstractBusinessController {

    private static final Logger LOG = LoggerFactory.getLogger(HandlerController.class);

    @Autowired
    private MethodRegistry methodRegistry;

    @Autowired
    private PingService pingService;

    @RequestMapping(value = "/handler/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OperationResult handle(@Valid @RequestBody HandledRequest request) throws BusinessException {
        MethodRegistry.Method method = methodRegistry.getMethod(request.getMethod());
        switch (method) {
            case PING:
                return wrap(pingService.ping(request.getUserId()));
            case UNKNOWN_METHOD:
                throw ExceptionFactory.unknownMethodExc(request.getMethod());
        }
        return null;
    }

    @Override
    protected Logger getLogger() {
        return LOG;
    }
}
