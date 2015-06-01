package com.glu.pong.rest.errors;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Error implements ErrorController {

    @RequestMapping(value = "/error")
    public void error(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int code = resp.getStatus();
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.setContentType("text/plain");
        resp.setContentType("UTF-8");
        resp.getWriter().append("Error code: " + code);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
