package com.ronald;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Redis Java驱动的使用
 * @author ronald
 * @date 2016年3月29日下午9:08:31
 *
 */
public class TestJedis {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.220.130", 6379);
        System.out.println("连接成功 ping:" + jedis.ping());

        //设置Key
        jedis.set("mykey", "abjio");
        System.out.println(jedis.get("mykey"));

        //List实例
        jedis.lpush("list", "张三","李四");
        List<String> list = jedis.lrange("list", 0, 1);
        for (String string : list) {
            System.out.println(string);
        }
        //获取所有的键
        Set<String> list2 = jedis.keys("*");
        for (String string : list2) {
            System.out.println("list2:" + string);
        }
    }
}
