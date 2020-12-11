package com.bcp;


import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Environment environment = this.applicationContext.getEnvironment();
        String protocol = "http";
        if (BooleanUtils.toBoolean(environment.getProperty("server.ssl.enabled"))) {
            protocol = "https";
        }
        String port = environment.getProperty("server.port");
        log.info("\n-------------------------------------------------------\n\tAplication '{}' is running! Access URLs:\n\tLocal: \t\t{}://localhost:{}\n\tProfile(s): \t{}\n-------------------------------------------------------", new Object[]{environment.getProperty("spring.application.name"), protocol, port, environment.getActiveProfiles()});
        log.info("StartApplication...");
    }
}