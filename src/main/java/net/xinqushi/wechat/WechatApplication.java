package net.xinqushi.wechat;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.util.Properties;

@SpringBootApplication
//扫描mapper接口
@MapperScan(basePackages = "net.xinqushi.wechat.web.dao")
public class WechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}

	@Bean
	public PageHelper pageHelper(){
		  PageHelper pageHelper = new PageHelper();
		  Properties properties = new Properties();
		  properties.setProperty("offsetAsPageNum","true");
		  properties.setProperty("rowBoundsWithCount","true");
		  properties.setProperty("reasonable","true");
		//配置mysql数据库的方言
		  properties.setProperty("dialect","mysql");
		  pageHelper.setProperties(properties);
		  return pageHelper;
		 }

//		 文件上传设置
		 @Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置文件大小限制 ,超出设置页面会抛出异常信息，
		// 这样在文件上传的地方就需要进行异常信息的处理了;
		factory.setMaxFileSize("256KB"); // KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("512KB");
		// Sets the directory location where files will be stored.
		// factory.setLocation("路径地址");
		return factory.createMultipartConfig();
	}

}
