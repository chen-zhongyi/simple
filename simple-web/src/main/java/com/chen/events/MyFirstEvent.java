package com.chen.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyFirstEvent implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyFirstEvent.class);

    @Override
    public void run(String... arg) throws Exception {
        logger.info("[my first event]");
    }
}
