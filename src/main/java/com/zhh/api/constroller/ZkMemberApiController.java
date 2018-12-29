package com.zhh.api.constroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.application.name}")
    private String instanceName;

    private final DiscoveryClient discoveryClient;

    @Autowired
    public ZkMemberApiController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/getMember")
    public String getmember() {
        return "this is member，我是会员服务:" + instanceName + "  端口号： " + serverPort;
    }

    @GetMapping("/services")
    public List<String> serviceUrl() {
        List<String> clientServices = discoveryClient.getServices();
        List<ServiceInstance> list = discoveryClient.getInstances(instanceName);
        List<String> services = new ArrayList<>();
        if (list != null && list.size() > 0) {
            list.forEach(serviceInstance -> services.add(serviceInstance.getUri().toString()));
        }
        return services;
    }
}
