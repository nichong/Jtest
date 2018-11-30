/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HellormiImpl
 * Author:   wh
 * Date:     2018/8/16 23:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.test.hello001.hellormi;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author wh
 * @create 2018/8/16
 * @since 1.0.0
 */
public class HellormiImpl implements Hellormi{

    @Override
    public void sayHello() {
        System.out.println("hello rmi");
    }

    public void sayHello1() {
        System.out.println("hello rmi");
        System.out.println("hello rmi1");
        System.out.println("hello rmi2");
        System.out.println("hello rmi3");
    }
}