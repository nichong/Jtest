/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Student
 * Author:   wh
 * Date:     2018/7/25 22:16
 * Description: student
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.test.bean;

/**
 * 〈一句话功能简述〉<br>
 * 〈student〉
 *
 * @author wh
 * @create 2018/7/25
 * @since 1.0.0
 */
public class Student {

    private String name;
    private int age;
    private String sex;

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
        System.out.println(this.name + "," + this.age + this.sex + "end");
        return super.toString();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}