package com.zhh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * zookeeper 会员客户端
 * <p> '@EnableDiscoveryClient' spring-cloud 使用非eureka（zookeeper，consul）做注册中心 <p>
 *
 * @author zhh.yin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudMemberZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMemberZookeeperApplication.class, args);
    }

}

