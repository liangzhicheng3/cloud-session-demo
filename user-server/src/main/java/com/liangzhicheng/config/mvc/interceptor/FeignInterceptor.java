package com.liangzhicheng.config.mvc.interceptor;

import com.liangzhicheng.common.utils.PrintUtil;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 问题：查看底层方法executeAndDecode(RequestTemplate template, Options options)执行时，请求模板中并没有任何的参数和请求头
 *       Session是依靠JSESSIONID进行识别，在SpringSession中，Session是依靠SESSION识别
 *       通过OpenFeign远程调用丢失了请求头，导致SESSIONID丢失，最终导致订单模块无法获取到User对象
 * 解决：
 *       创建请求拦截器（FeignInterceptor），在请求模板生成前对请求进行处理
 *       （将原Request对象中的Cookie请求头信息设置给请求模板，这样OpenFeign创建的请求就具有了Cookie内容）
 */
@Configuration
public class FeignInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            PrintUtil.info("[用户服务] 远程调用前执行RequestInterceptor处理请求信息...");
            ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            requestTemplate.header("Cookie", request.getHeader("Cookie"));
        };
    }

}
