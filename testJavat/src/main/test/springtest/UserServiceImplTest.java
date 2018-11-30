package springtest;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.test001.UserService;
import spring.test001.UserServiceImpl;

public class UserServiceImplTest {
    @Test//原生方式调用Service 是通过new的方式来进行调用对象
    public void testSayHello() throws Exception {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.sayHello();
    }

    //使用SpringIoC 再Spring工厂来获取对象的实例
    @Test
    public void run2() {
        //通过加载applicationContext.xml 创建Spring工厂
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //调用Spring工厂中的UserService实例  Spring工厂中有什么取决你再applicationContext.xml中配置什么
        UserService us = (UserService) ac.getBean("UserService");
        //调用这个对象的sayHello()方法
        us.sayHello();

        UserService userServiceImpl = new UserServiceImpl();
        System.out.println(us == userServiceImpl);
        System.out.println(us.hashCode());
        System.out.println(userServiceImpl.hashCode());
    }

    //使用SpringIoC 再Spring工厂来获取对象的实例
    @Test
    public void run3() {
        //通过加载applicationContext.xml 创建Spring工厂
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        //调用Spring工厂中的UserService实例  Spring工厂中有什么取决你再applicationContext.xml中配置什么
        UserService us1 = (UserService) ac.getBean("UserService1");
        //调用这个对象的sayHello()方法
        us1.sayHello();

        UserService us = (UserService) ac.getBean("UserService");
        us.sayHello();

        System.out.println(us1 == us);
        System.out.println(us.hashCode());
        System.out.println(us1.hashCode());

        UserService us2 = us1;
        us = us1;
        System.out.println("us1 ==us2:" + (us1 == us2));
        System.out.println("us ==us2:" + (us == us2));
        System.out.println(us1 == us);
    }

    @Test
    public void run4() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserService us = (UserService) ac.getBean("UserService");
        /*
            <import resource="classpath:springapp2.xml"/>  spring.test001.copy.UserServiceImpl
            <import resource="classpath:springapp1.xml"/>  spring.test001.UserServiceImpl
            因为springapp1 覆盖了 springapp2，所以总是打印 spring.test001.UserServiceImpl
         */
        System.out.println(us.getClass().getName());//spring.test001.UserServiceImpl
    }

    @Test
    public void run5() {

        while (true) {
            Byte[] bytes = new Byte[1024 * 1024 * 1024];
        }
    }
}