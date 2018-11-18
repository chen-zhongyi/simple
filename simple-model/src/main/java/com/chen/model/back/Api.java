package com.chen.model.back;

import com.chen.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Api extends BaseModel {

    @Column(columnDefinition = STRING + "'请求方法'")
    private String method;
    @Column(columnDefinition = STRING + "'content type'")
    private String contentType;
    @Column(columnDefinition = STRING + "'host'")
    private String host;
    @Column(columnDefinition = STRING + "'uri'")
    private String uri;
    @Column(columnDefinition = STRING_1000 + "'headers'")
    private String headers;
    @Column(columnDefinition = STRING_TEXT + "'params'")
    private String params;
    @Column(columnDefinition = STRING + "'action'")
    private String action;
    @Column(columnDefinition = STRING_TEXT + "'body'")
    private String body;
    @Column(columnDefinition = STRING_TEXT + "'exceptions'")
    private String exceptions;
    @Column(columnDefinition = STRING_TEXT + "'result'")
    private String result;
    @Column(columnDefinition = LONG + "'开始时间'")
    private Long startTime;
    @Column(columnDefinition = LONG + "'结束时间'")
    private Long endTime;
    @Column(columnDefinition = LONG + "'执行时间毫秒'")
    private Long exeTime;
    @Column(columnDefinition = LONG + "'用户ID'")
    private Long personId;
    @Column(columnDefinition = STRING + "'用户名称'")
    private String personName;
    @Column(columnDefinition = STRING + "'token'")
    private String token;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getExceptions() {
        return exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getExeTime() {
        return exeTime;
    }

    public void setExeTime(Long exeTime) {
        this.exeTime = exeTime;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
