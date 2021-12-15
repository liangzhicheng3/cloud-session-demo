package com.liangzhicheng.modules.controller;

import com.liangzhicheng.common.response.ResponseResult;
import com.liangzhicheng.common.utils.JSONUtil;
import com.liangzhicheng.common.utils.PrintUtil;
import com.liangzhicheng.entity.User;
import com.liangzhicheng.modules.feign.IOrderFeignApi;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private IOrderFeignApi orderFeignApi;

    @ResponseBody
    @GetMapping(value = "/login")
    public Object login(User user, HttpSession session){
        String username = user.getUsername();
        String password = user.getPassword();
        ResponseResult responseResult = new ResponseResult();
        if("admin".equals(username) && "123456".equals(password)){
            responseResult.setCode(200);
            responseResult.setMessage("登录成功");
//            String userInfo = JSONUtil.toJSONString(user);
//            stringRedisTemplate.opsForValue().set("session", userInfo);
            session.setAttribute("user", user);
        }else{
            responseResult.setCode(-1);
            responseResult.setMessage("登录失败");
        }
        return responseResult;
    }

    @RequestMapping(value = "/test")
    public String test(){
        String result = orderFeignApi.order();
        PrintUtil.info("[用户服务] 远程调用订单服务");
        return result;
    }

}
