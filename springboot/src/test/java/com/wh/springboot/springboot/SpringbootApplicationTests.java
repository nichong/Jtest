package com.wh.springboot.springboot;

import com.wh.springboot.springboot001.aspect.MainConfigOfAOP;
import com.wh.springboot.springboot001.aspect.MathCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Test
	public void contextLoads() {
	}

	/**
	 * 测试基于spring注解的切面 测试用例
	 */
	@Test
	public void testAop(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		mathCalculator.div(1,0);
	}

}
