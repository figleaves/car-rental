package com.demo.carrental.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class RequestHolder implements Serializable {

    private static final String USER_ID = "userId";

    public static void setUserId(HttpServletRequest request, String userId){
        request.setAttribute(USER_ID, userId);
    }

    public static String getUserId(HttpServletRequest request) {
        return (String) request.getAttribute(USER_ID);
    }

    public static String getUserId() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return getUserId(request);
    }
}
