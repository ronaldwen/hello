package com.ronald;

import com.ronald.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * 测试Apache Commons BeanUtils 包中的相关方法
 */
public class TestCommonBeanUtils {

    public static void main(String[] args) {
        TestCommonBeanUtils cb = new TestCommonBeanUtils();

        cb.testBeanUtils();
    }

    /**
     * 测试BeanUtils 类相关方法
     */
    public void testBeanUtils(){
        User user = new User("1", "ronald", "飞", 15, "北京");
        User newU = new User();
            //拷贝对象， 浅拷贝
        try {
            BeanUtils.copyProperties(newU, user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(newU);
    }
}
