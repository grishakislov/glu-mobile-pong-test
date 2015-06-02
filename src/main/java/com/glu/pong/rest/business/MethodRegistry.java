package com.glu.pong.rest.business;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MethodRegistry {

    public enum Method {
        PING,
        UNKNOWN_METHOD
    }

    private class RegisteredMethod {
        private Method method;
        private String path;
        private String description;

        public RegisteredMethod(Method method, String path, String description) {
            this.method = method;
            this.path = path;
            this.description = description;
        }

        public Method getMethod() {
            return method;
        }

        public String getPath() {
            return path;
        }

        public String getDescription() {
            return description;
        }
    }

    private Map<String, RegisteredMethod> methods = new HashMap<>();

    @PostConstruct
    private void init() {
        registerMethod(Method.PING, "ping", "Ping method");
    }

    private void registerMethod(Method method, String path, String description) {
        if (method == Method.UNKNOWN_METHOD) {
            throw new IllegalArgumentException("Cannot register this method (UNKNOWN_METHOD)");
        }
        methods.put(path, new RegisteredMethod(method, path, description));
    }

    public Method getMethod(String path) {
        if (methods.containsKey(path)) {
            return methods.get(path).getMethod();
        } else {
            return Method.UNKNOWN_METHOD;
        }
    }

}
