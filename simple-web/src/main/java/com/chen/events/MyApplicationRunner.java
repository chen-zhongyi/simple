package com.chen.events;

import com.chen.utils.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(MyApplicationRunner.class);

    @Autowired
    private PropertyUtil propertyUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("[server start] env   : " + propertyUtil.getProperty("spring.profiles.active"));
        logger.info("[server start] dbUrl : " + propertyUtil.getProperty("spring.datasource.url"));
        logger.info("[server start] host  : " + propertyUtil.getHost());
    }

}
