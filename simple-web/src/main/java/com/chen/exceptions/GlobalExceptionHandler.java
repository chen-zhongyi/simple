package com.chen.exceptions;

import com.chen.service.back.ApiService;
import com.chen.vos.ApiVO;
import com.chen.vos.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ApiService apiService;

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        Result result = Result.failed();
        ApiVO apiVO = new ApiVO();
        apiVO.setResult(objectMapper.writeValueAsString(result));
        apiService.edit((Long) request.getAttribute("apiId"), apiVO);
        return result;
    }

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest request, BizException bizException) throws Exception{
        Result result = Result.failed(bizException.getCode());
        ApiVO apiVO = new ApiVO();
        apiVO.setResult(objectMapper.writeValueAsString(result));
        apiService.edit((Long) request.getAttribute("apiId"), apiVO);
        return result;
    }

}
