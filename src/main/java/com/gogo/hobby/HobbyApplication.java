package com.gogo.hobby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yang
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableCaching
public class HobbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HobbyApplication.class, args);
    }

}
