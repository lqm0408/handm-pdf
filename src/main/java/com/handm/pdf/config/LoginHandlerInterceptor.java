package com.handm.pdf.config;

import com.handm.pdf.exception.LoginException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * @author lqm
 * @date 2024/1/6 17:54
 */

public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final String PASS = "&dZZ7YcFIBD%tWH4aqV@#kzFWi%jl0wJ*3RG";

    @Override
    @SuppressWarnings("all")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String pass = request.getHeader("pass");
        if (Objects.equals(PASS, pass)) {
            return true;
        }
        throw new LoginException("login required!");
    }
}
