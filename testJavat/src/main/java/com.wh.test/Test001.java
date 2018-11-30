package com.wh.test;

import com.wh.test.bean.Student;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Test001 {
    public static void main(String []args){
        //testMap2Bean();
    }





    private static void testMap2Bean() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhansan");
        map.put("age", 20);
        map.put("sex", null);
        Student s = map2Bean(map, Student.class);
        s.toString();
    }

    /**
     *
     *
     * Map转换层Bean，使用泛型免去了类型转换的麻烦。
     * @param <T>
     * @param map
     * @param class1
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
