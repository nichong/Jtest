/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Test001
 * Author:   wh
 * Date:     2018/4/15 14:02
 * Description: 测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.wh.springboot.springboot;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈java 8 stream练习〉<br>
 *     https://blog.csdn.net/happyheng/article/details/52832313
 *     https://blog.csdn.net/zhou85xin/article/details/52171190
 * 〈测试〉
 *
 * @author wh
 * @create 2018/4/15
 * @since 1.0.0
 */
public class Test001 {
    public static void main(String[] args) {
        /*
         * 一、Stream API 的操作步骤：
         * 1. 创建 Stream
         * 2. 中间操作  :map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
         * 3. 终止操作(终端操作):forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
         */
        //一.获取stream
/*      //1. Collection 提供了两个方法  stream() 与 parallelStream()
		List<String> list = new ArrayList<>();
		Stream<String> stream = list.stream(); //获取一个顺序流
		Stream<String> parallelStream = list.parallelStream(); //获取一个并行流

		//2. 通过 Arrays 中的 stream() 获取一个数组流
		Integer[] nums = new Integer[10];
		Stream<Integer> stream1 = Arrays.stream(nums);

		//3. 通过 Stream 类中静态方法 of()
		Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6);

		//4. 创建无限流
		//迭代
		Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
		stream3.forEach(System.out::println);

		//生成
		Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
		stream4.forEach(System.out::println);*/



        //二.stram的一些操作： 中间操作不产生结果，当
        /*1、遍历操作(map)：
        使用map操作可以遍历集合中的每个对象，并对其进行操作，map之后，
        用.collect(Collectors.toList())会得到操作后的集合
        */
        //1.1、遍历转换为大写：
        List<String> list = Arrays.asList("a","b","c","d","e","f","weihao");
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(str->System.out.println(str));

        //1.2、平方数： 并过滤掉平方后卫16的数
        List<Integer> nums = Arrays.asList(5, 2, 6,10,4,8);
        nums.stream().
                map(n -> n * n).
                collect(Collectors.toList()).
                //2、过滤操作(filter)：使用filter可以对象Stream中进行过滤，通过测试的元素将会留下来生成一个新的Stream。
                stream().filter(i -> i != 16).collect(Collectors.toList()).
            //5、排序（sort/min/max/distinct）：
                 //sort可以对集合中的所有元素进行排序。max，min可以寻找出流中最大或者最小的元素，而distinct可以寻找出不重复的元素：
                stream().sorted((x,y)-> x-y).collect(Collectors.toList()).
                forEach(i->System.out.println(i));

        //4、返回特定的结果集合（limit/skip）： limit 返回 Stream 的前面 n 个元素；skip 则是扔掉前 n 个元素:
        boolean isAllMath = list.stream().skip(2).limit(3).collect(Collectors.toList()).//跳过a,b ,剩下c,d,e,f,weihao, 再输出前三个
        /*6、匹配(Match方法)：
           有的时候，我们只需要判断集合中是否全部满足条件，或者判断集合中是否有满足条件的元素，这时候就可以使用match方法：
            allMatch：Stream 中全部元素符合传入的 predicate，返回 true
            anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
            noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
         */
        stream().allMatch(s->s.equals("c"));
        System.out.println(isAllMath);//所有的串都包含为 c  ，显然为false
               // stream().forEach(s -> System.err.println(s));//c,d,e
    }

}