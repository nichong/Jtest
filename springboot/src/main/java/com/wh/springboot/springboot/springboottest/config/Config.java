/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Config
 * Author:   wh
 * Date:     2018/5/17 22:34
 * Description: 这是一个配置文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.springboot.springboot.springboottest.config;

import com.wh.springboot.springboot.springboottest.data.Person;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈这是一个配置文件〉
 *
 * @author wh
 * @create 2018/5/17
 * @since 1.0.0
 */
@Configuration
@ComponentScan(value = "com.wh.springboot.springboot.springboottest", excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = {RestController.class, Service.class})
})//扫描包，排除： 注解为RestController、Service的类
public class Config {

    @Bean(value = "p1")
    //@Scope(value = "prototype")
    public Person person(){
        return  new Person("BOb", 20);
    }

}