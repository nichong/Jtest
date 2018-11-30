/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Jvmtest001
 * Author:   wh
 * Date:     2018/8/30 22:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.test.hello001.jvm.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author wh
 * @create 2018/8/30
 * @since 1.0.0
 */
public class Jvmtest001 {
    public static void main(String[] args) {
        List<Byte[]> list=new ArrayList<Byte[]>();
        for(int i=0;i<100;i++){
            //构造1M大小的byte数值
            Byte[] bytes=new Byte[1024*1024];
            //将byte数组添加到list列表中，因为存在引用关系所以bytes数组不会被GC回收
            list.add(bytes);
        }
    }
}