/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Person
 * Author:   wh
 * Date:     2018/5/17 22:33
 * Description: 这是一个Bean
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.springboot.springboot.springboottest.data;

/**
 * 〈一句话功能简述〉<br> 
 * 〈这是一个Bean〉
 *
 * @author wh
 * @create 2018/5/17
 * @since 1.0.0
 */
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}