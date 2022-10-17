package com.ngtechnology.estacionamento.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model) throws Exception {
    }

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {


        if ((request.getHeader("Partner1") == "Star-Park") || (request.getHeader("Partner2") == "Center-Park")) {
            response.getWriter().write("something");
            response.setStatus(400);

            return false;
        }
        return true;
    }
}
