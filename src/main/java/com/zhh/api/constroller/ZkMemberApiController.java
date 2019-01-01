package com.zhh.api.constroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Value("${server.port}")
    private String serverPort;

    private final DiscoveryClient discoveryClient;

    public ZkMemberApiController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/getMember")
    public String getmember() {
        List<ServiceInstance> instances = discoveryClient.getInstances("yinzhh-member-zk");
        List<String> services = discoveryClient.getServices();
        System.out.println(services);

        for (ServiceInstance instance : instances) {
            String scheme = instance.getScheme();
            System.out.println(scheme);
        }

        return "this is member，我是会员zookeeper服务  端口号： " + serverPort;
    }
}
