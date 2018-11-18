package com.chen.aops;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.chen.annotations.NotNeedLogin;
import com.chen.exceptions.BizException;
import com.chen.utils.JwtUtil;
import com.chen.utils.PropertyUtil;
import com.chen.vos.Result;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Order(2)
@Component
public class AccesstokenManager {

    @Autowired
    private PropertyUtil propertyUtil;

    @Before(value = "(execution(* com.chen.controllers.*.*(..)) || execution(* com.chen.controllers.*.*.*(..))) " +
            "&& (! within(com.chen.controllers.BaseController))")
    public void tokenChecking(JoinPoint jp) {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        Annotation annotation = method.getAnnotation(NotNeedLogin.class);
        if (annotation == null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("accesstoken");
            DecodedJWT jwt = JwtUtil.verify(token, propertyUtil.getSecret(), propertyUtil.getHost());
            if (jwt == null) {
                throw new BizException(Result.StatusCode.SYSTEM_TOKEN_UNVALID);
            }
        }
    }
}
