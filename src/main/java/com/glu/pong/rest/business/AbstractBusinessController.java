package com.glu.pong.rest.business;

import com.glu.pong.transport.BusinessException;
import com.glu.pong.transport.OperationResult;
import com.glu.pong.utils.JsonUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractBusinessController {

    protected OperationResult wrap(Object payload) {
        OperationResult response = new OperationResult();
        response.setCode(OperationResult.OK);
        response.setMessage("Ok");
        response.setPayload(payload);
        return response;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleBusinessException(BusinessException e) {
        getLogger().error(e.getMessage());
        OperationResult operationResult = new OperationResult();
        operationResult.setMessage(e.getMessage());
        operationResult.setCode(e.getCode());
        return JsonUtils.encode(operationResult);
    }

    @ExceptionHandler(PersistenceException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handlePersistenceException(PersistenceException e) {
        getLogger().error(e.getMessage());
        OperationResult operationResult = new OperationResult();
        operationResult.setCode(OperationResult.INTERNAL_DATABASE_ERROR);
        return JsonUtils.encode(operationResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        getLogger().error(e.getMessage());
        OperationResult operationResult = new OperationResult();
        operationResult.setCode(OperationResult.ARGUMENT_VALIDATION_ERROR);
        String message = "Request validation error";
        if (e.getBindingResult().getAllErrors().size() > 1) {
            message += "s: ";
        } else {
            message += ": ";
        }
        operationResult.setMessage(message + exceptionToString(e));
        return JsonUtils.encode(operationResult);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        getLogger().error(e.getMessage());
        OperationResult operationResult = new OperationResult();
        operationResult.setCode(OperationResult.INTERNAL_ERROR);
        operationResult.setMessage("Internal server error");
        return JsonUtils.encode(operationResult);
    }

    private String exceptionToString(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();

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

    protected abstract Logger getLogger();

}
