package com.zhh.api.constroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: 会员服务控制层
 * @Description:
 * @Author: yin.zhh
 * @Date 2018-12-23 15:08
 * @Version v.1.0.0
 */
@RestController
public class ZkMemberApiController {

    private static final Logger Logger = LoggerFactory.getLogger(ZkMemberApiController.class);

    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.application.name}")
    private String instanceName;

    private final DiscoveryClient discoveryClient;

    public ZkMemberApiController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/getMember")
    public String getmember() {
        Logger.info("服务名： " + instanceName);
        return "this is member，我是会员zookeeper服务  端口号： " + instanceName + "  端口号： " + serverPort;
    }

    @GetMapping("/services")
    public List<String> serviceUrl() {
        List<ServiceInstance> instances = discoveryClient.getInstances(instanceName);
        List<String> services = discoveryClient.getServices();
        Logger.info("服务名： " + services);
        if (instances != null && instances.size() > 0) {
            instances.forEach(serviceInstance -> services.add(serviceInstance.getUri().toString()));
        }
        return services;
    }
}
