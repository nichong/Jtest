/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Test001
 * Author:   wh
 * Date:     2018/9/7 20:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package sjjgysf;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author wh
 * @create 2018/9/7
 * @since 1.0.0
 */
public class Test001 {
    public static void main(String[] args) {
        int []a = {1,3,5,7,9,11,13,15};
        int x = 1;

        int l = 0;
        int h = a.length;
        int halt = -1;

        while (true){
            halt = (l+h)/2;
            if(l >= h){
                halt = -1;
                break;
            }
            else if(a[halt] == x){
                //index = halt;
                break ;
            }else{
                if(a[halt] < x){
                    l = halt + 1;
                }else {
                    h = halt -1;
                }
            }
        }
        System.out.println(halt);

    }

}