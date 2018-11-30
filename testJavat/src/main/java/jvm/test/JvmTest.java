/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JvmTest
 * Author:   wh
 * Date:     2018/8/30 22:58
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package jvm.test;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author wh
 * @create 2018/8/30
 * @since 1.0.0
 */
public class JvmTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
           System.gc();
            //申请堆外内存，这个内存是本地的直接内存，并非java堆内存
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024*1024*10);
            System.out.println("created "+i+" byteBuffer");
        }
    }

}