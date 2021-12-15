package com.liangzhicheng.config.mvc.interceptor.details;

import com.liangzhicheng.common.utils.JSONUtil;
import com.liangzhicheng.common.utils.PrintUtil;
import com.liangzhicheng.config.context.SpringContextHolder;
import com.liangzhicheng.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //手动获取StringRedisTemplate对象
//        StringRedisTemplate stringRedisTemplate = SpringContextHolder.getBean(StringRedisTemplate.class);
//        String userInfo = stringRedisTemplate.opsForValue().get("session");
//        User user = JSONUtil.parseObject(userInfo, User.class);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        PrintUtil.info("[订单服务] 登录拦截器中获取session：{}", user);
        if(user == null){
            PrintUtil.info("用户未登录...", null);
            return false;
        }else{
            PrintUtil.info("用户已登录...", null);
            return true;
        }
    }

}
