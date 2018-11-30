package com.wh.springboot.springboot001;

import com.wh.springboot.springboot.springboottest.config.Config;
import com.wh.springboot.springboot.springboottest.controller.WorldController;
import com.wh.springboot.springboot.springboottest.data.Person;
import com.wh.springboot.springboot001.aspect.MainConfigOfAOP;
import com.wh.springboot.springboot001.aspect.MathCalculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
@EnableConfigurationProperties
public class SpringbootApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringbootApplication.class, args);
		System.out.println("hello spring boot001");

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		mathCalculator.div(1,0);
	}

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello spring boot method");
		return "hello,this is a springboot demo";
	}
}
