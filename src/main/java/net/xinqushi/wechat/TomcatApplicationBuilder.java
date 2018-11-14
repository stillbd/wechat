package net.xinqushi.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
*@author wangwei
*@created 0:35 2018/8/20
 *@classname TomcatApplicationBuilder
*@classdescription  tomcat启动
*
*/
//@SpringBootApplication
public class TomcatApplicationBuilder  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WechatApplication.class);
    }
}



