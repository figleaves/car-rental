package com.demo.carrental.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.demo.carrental.common.BusException;
import com.demo.carrental.common.ErrorCode;
import com.demo.carrental.common.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenFilter implements HandlerInterceptor {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info(request.getRequestURI());
        String token = request.getHeader(jwtTokenUtil.header);
        if (StringUtils.isEmpty(token)){
            throw new BusException(ErrorCode.INVALID_TOKEN);
        }
        log.info("token:{}", token);

        Claims claims = null;
        try {
            claims = jwtTokenUtil.parseJwt(token);
        } catch (Exception e){
            log.info("invalid token");
            throw new BusException(ErrorCode.INVALID_TOKEN);
        }
        if (null == claims){
            throw new BusException(ErrorCode.INVALID_TOKEN);
        }

        if (jwtTokenUtil.checkAndRenewTokenExpiration(token, claims.getId())){
            return true;
        } else {
            throw new BusException(ErrorCode.TOKEN_EXPIRED);
        }
    }
}
