package net.xinqushi.wechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * created by wangyu
 * 2018/11/8 10:30
 * 微信公众号相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String mpAppId;

    private String mpAppSecret;

    /** 项目部署所在域名 */
    private String yuming;
}
