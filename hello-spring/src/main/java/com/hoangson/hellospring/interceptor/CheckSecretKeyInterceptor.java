package com.hoangson.hellospring.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckSecretKeyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Test Interceptor preHandle ------->");


        HandlerMethod handlerMethod = (HandlerMethod) handler;
        SecretKeyRequired secretKeyRequired = handlerMethod.getMethod().getAnnotation(SecretKeyRequired.class);
        if (secretKeyRequired == null) {
            return true;
        } else {
            String secretToken = request.getHeader("secretKey");
            System.out.println("Test Interceptor secretToken  ------->" + secretToken);
            if ("123HoangVanSon@".equals(secretToken))
                return true;
            else throw new Exception("CheckSecretKeyInterceptor");

        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("Test Interceptor postHandle ------->");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {

        System.out.println("Test Interceptor afterCompletion ------->");
    }
}
