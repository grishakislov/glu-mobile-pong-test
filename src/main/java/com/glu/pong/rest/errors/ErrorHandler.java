package com.glu.pong.rest.errors;

import com.glu.pong.transport.BusinessException;
import com.glu.pong.transport.OperationResult;
import com.glu.pong.utils.JsonUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ErrorHandler implements HandlerExceptionResolver, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);

    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) {
        OperationResult result = new OperationResult();
        try {
            if (e instanceof MethodArgumentNotValidException) {

                LOG.error(e.getMessage());
                result.setCode(OperationResult.ARGUMENT_VALIDATION_ERROR);
                result.setMessage("Request validation error(s): "
                        + exceptionToString((MethodArgumentNotValidException) e));

            } else if (e instanceof PersistenceException) {

                LOG.error(e.getMessage());
                result.setCode(OperationResult.INTERNAL_DATABASE_ERROR);

            } else if (e instanceof TypeMismatchException) {

                LOG.error(e.getMessage());
                result.setCode(OperationResult.TYPE_MISMATCH);
                result.setMessage("Type mismatch while parsing request");

            } else if (e instanceof BusinessException) {

                BusinessException be = (BusinessException) e;
                result.setMessage(be.getMessage());
                result.setCode(be.getCode());
                LOG.error(e.getMessage());

            } else {
                LOG.error(e.getMessage());
                result.setCode(OperationResult.INTERNAL_ERROR);
                result.setMessage("Internal error");
            }

            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().write(JsonUtils.encode(result));

        } catch (IOException io) {
            io.printStackTrace();
        }
        return new ModelAndView();
    }

    private String exceptionToString(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();

        //Errors: field = {}, error = {};
        bindingResult.getAllErrors().forEach((oe) -> {
            if (oe instanceof FieldError) {
                FieldError fieldError = (FieldError) oe;
                stringBuilder.append("field '").append(fieldError.getField()).append("' ");
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append("; ");
            }
        });

        return stringBuilder.toString();
    }

}
