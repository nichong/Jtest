package com.wh.springboot.springboot;

import com.wh.springboot.springboot.springboottest.config.Config;
import com.wh.springboot.springboot.springboottest.config.Swagger2;
import com.wh.springboot.springboot.springboottest.controller.WorldController;
import com.wh.springboot.springboot.springboottest.data.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@RestController
@EnableConfigurationProperties
public class SpringbootApplication {

	@Value("${spring.name}")
	public void setName(String qname) {
		SpringbootApplication.xname = qname;
	}

	private static String xname ;



	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
		System.out.println("hello spring boot");
		System.out.println("static name:"+xname);
		ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Swagger2.class);

/*		ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(Config.class);
		WorldController wc = (WorldController)applicationContext.getBean("worldController");
		wc.dis();
		Person p = (Person) applicationContext.getBean("p1");
		Person p1 = (Person) applicationContext.getBean("p1");
		System.out.println(p.toString());
		System.out.println(p == p1);
		//获取容器中所有的Bean
		String []names = applicationContext.getBeanDefinitionNames();
		Arrays.asList(names).stream().forEach(s-> System.out.println(s));*/
	}

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello spring boot method");
		return "hello,this is a springboot demo";
	}



	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static Set<String> getBetweenDates(String start, String end) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(start);
			endTime = sdf.parse(end);
		} catch (ParseException e) {

		}
		Set<String> result = new HashSet<>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(startTime);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(endTime);
		while (tempStart.before(tempEnd)) {
			result.add(sdf.format(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}
}
