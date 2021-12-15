package com.liangzhicheng.modules.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service")
public interface IOrderFeignApi {

    @GetMapping(value = "/order")
    String order();

}
