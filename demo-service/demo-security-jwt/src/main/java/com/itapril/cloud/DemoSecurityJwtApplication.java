package com.itapril.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by itapril on 2018/5/22 22:32.
 */
/**
 *
 * 如果启动报mongo连接超时 使用下面的配置
 *
 * 由于在pom里面引用了
 * spring-boot-starter-data-mongodb
 * 会导致程序启动的时候回去连接mongodb 在pom里面去掉依赖就行了
 *
 * //@SpringBootApplication(exclude = {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
 */
@SpringBootApplication
public class DemoSecurityJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityJwtApplication.class, args);
    }
}
