package net.xinqushi.wechat.config;

import net.xinqushi.wechat.interceptor.LoginInterceptor;
import net.xinqushi.wechat.interceptor.ReadInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置类
 * 配置拦截器
 */
@SpringBootConfiguration
public class MySpringBootConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private ReadInterceptor readInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/page/qediter1");
        registry.addInterceptor(readInterceptor).addPathPatterns("/summary/detail/get/*");
    }
}
