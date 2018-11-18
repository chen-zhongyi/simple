package com.chen.aops;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.chen.model.back.Api;
import com.chen.service.back.ApiService;
import com.chen.utils.JwtUtil;
import com.chen.utils.PropertyUtil;
import com.chen.vos.ApiVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Order(1)
@Component
public class LogManager {

    private Logger logger = LoggerFactory.getLogger(LogManager.class);

    @Autowired
    private ApiService apiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PropertyUtil propertyUtil;

    @Pointcut(value = "(execution(* com.chen.controllers.*.*(..)) || execution(* com.chen.controllers.*.*.*(..))) " +
            "&& (! within(com.chen.controllers.BaseController))")
    public void controllerLog(){}

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        Long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("accesstoken");
        DecodedJWT jwt = JwtUtil.verify(token, propertyUtil.getSecret(), propertyUtil.getHost());
        logger.info("[start         ]:" + "------------");
        logger.info("[request method]:" + request.getMethod());
        logger.info("[content type  ]:" + request.getContentType());
        logger.info("[request host  ]:" + request.getRemoteHost());
        logger.info("[request uri   ]:" + request.getRequestURI());
        logger.info("[accesstoken   ]:" + token);
        Map<String, List<String>> headers = new HashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()) {
            String header = enumeration.nextElement();
            List<String> list = new ArrayList<>();
            Enumeration<String> e = request.getHeaders(header);
            while(e.hasMoreElements()) {
                list.add(e.nextElement());
            }
            headers.put(header, list);
        }
        logger.info("[headers       ]:" + objectMapper.writeValueAsString(headers));
        logger.info("[params        ]:" + objectMapper.writeValueAsString(request.getParameterMap()));
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        String action = jp.getTarget().getClass().getName() + "." + jp.getSignature().getName();
        logger.info("[action        ]:" + action);
        Annotation[][] methodAnnotations = method.getParameterAnnotations();
        String body = null;
        for (int i = 0; i < methodAnnotations.length; ++i) {
            Annotation[] annotations = methodAnnotations[i];
            for (Annotation annotation : annotations) {
                if (annotation instanceof RequestBody) {
                    body = new Gson().toJson(jp.getArgs()[i]);
                    logger.info("[body          ]:" + body);
                    break;
                }
            }
        }
        Object data = null;
        String error = null;
        try {
            data = jp.proceed();
        } catch (Throwable e){
            logger.info("[error         ]:", e);
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            error = stringWriter.toString();
            stringWriter.close();
            printWriter.close();
            throw e;
        } finally {
            Long endTime = System.currentTimeMillis();
            logger.info("[exe time      ]:" + (endTime - startTime) + "ms");
            logger.info("[response      ]:" + objectMapper.writeValueAsString(data));
            logger.info("[end           ]:" + "------------");
            ApiVO vo = new ApiVO();
            vo.setMethod(request.getMethod());
            vo.setContentType(request.getContentType());
            vo.setHost(request.getRemoteHost());
            vo.setUri(request.getRequestURI());
            vo.setHeaders(objectMapper.writeValueAsString(headers));
            vo.setParams(objectMapper.writeValueAsString(request.getParameterMap()));
            vo.setAction(action);
            vo.setBody(body);
            vo.setExceptions(error);
            vo.setStartTime(startTime);
            vo.setEndTime(endTime);
            vo.setExeTime(endTime - startTime);
            vo.setResult(objectMapper.writeValueAsString(data));
            vo.setToken(token);
            if (jwt != null) {
                vo.setPersonId(jwt.getClaim("id").asLong());
                vo.setPersonName(jwt.getClaim("name").asString());
            }
            Api api = apiService.add(vo);
            request.setAttribute("apiId", api.getId());
        }
        return data;
    }
}
