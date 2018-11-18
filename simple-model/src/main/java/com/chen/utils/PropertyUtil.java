package com.chen.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertyUtil {

    private static final String HOST = "chen.host";
    private static final String SECRET = "chen.secret";

    @Autowired
    private Environment environment;

    public String getHost() {
        return environment.getProperty(HOST);
    }

    public String getSecret() {
        return environment.getProperty(SECRET);
    }

    public String getProperty(String key) {
        return environment.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

}
