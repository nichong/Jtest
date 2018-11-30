/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: HelloController
 * Author:   wh
 * Date:     2018/5/17 22:54
 * Description: sd
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.springboot.springboot.springboottest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈sd〉
 *
 * @author wh
 * @create 2018/5/17
 * @since 1.0.0
 */
@Controller
public class WorldController {
    @Value("${test.msg}")
    private  String str;
    public void dis(){
        System.out.println(str);
    }
}