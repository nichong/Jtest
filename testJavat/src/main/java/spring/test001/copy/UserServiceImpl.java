/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   wh
 * Date:     2018/8/15 23:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package spring.test001.copy;

import org.springframework.beans.factory.annotation.Autowired;
import spring.test001.NameSerice;
import spring.test001.UserService;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author wh
 * @create 2018/8/15
 * @since 1.0.0
 */
public class UserServiceImpl implements  UserService {

    @Autowired
    private NameSerice nameSerice;


    @Override
    public void sayHello() {
        System.out.println("hello Spring!!!");
        nameSerice.disName();
    }
}