package com.liangzhicheng.modules.controller;

import com.liangzhicheng.common.utils.PrintUtil;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping(value = "/order")
    public String order(@CookieValue("JSESSIONID") String jSessionId) {
        PrintUtil.info("[订单服务] 获取session：{}", jSessionId);
        return "success";
    }

}
