package com.example.springboot.data.datajdbc.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource druid(){
        return  new DruidDataSource();
    }

    //配置Durid监控
    //配置一个后台管理的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParams=new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认允许所有访问
        initParams.put("deny","192.162.122.2");//拒绝的访问
        bean.setInitParameters(initParams);
        return  bean;
    }
    //配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
       FilterRegistrationBean bean= new FilterRegistrationBean();
       bean.setFilter(new WebStatFilter());
       Map<String,String> initParams=new HashMap<>();
       initParams.put("exclusions","*.js,*.css,/druid/*");
       bean.setInitParameters(initParams);
       bean.setUrlPatterns(Arrays.asList("/*"));
       return bean;
    }
}
