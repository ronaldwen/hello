package com.ronald;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 测试定时器的使用
 * @author ronald
 * @date 2016年5月19日下午4:43:47
 */
public class TestTimer {
    volatile static boolean stop = true;
    static Map<String, Timer> map = new HashMap<>();


    public static void main(String[] args) {
        TestTimer test = new TestTimer();

        //延长指定时间（毫秒）后开始执行任务
        Timer timer = new Timer();
        timer.schedule(test.new Task1("task1"), 1000,2000);
        map.put("task1", timer);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse("2016-05-19 17:18:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //指定时间执行任务
//		timer.schedule(test.new Task1(), d);

        //延长指定时间后，按固定间隔时间执行（毫秒）
        ////与scheduleAtFixedRate 不同的是：间隔时间在任务执行完后开始计时
        Timer timer2 = new Timer();
        map.put("task2", timer2);
        timer2.schedule(test.new Task1("task2"), 0, 1000);
        //延长指定时间后，按固定间隔时间执行（毫秒）
//		timer.scheduleAtFixedRate(test.new Task1(), 1000, 5000);



    }

    class Task1 extends TimerTask {

        private String taskName;
        private int i = 0;

        public Task1() {
            super();
        }

        public Task1(String taskName) {
            super();
            this.taskName = taskName;
        }

        @Override
        public void run() {
            i++;
            System.out.println(taskName + "--定时器执行。。。" + i + ":" + new Date());
            if(i == 5) {
                Timer timer = map.get(taskName);
                timer.cancel();
                map.remove(taskName);
            }
        }

    }

    class Task2 extends TimerTask {


        @Override
        public void run() {

            try {
                Thread.sleep(2000);
                System.out.println("定时器执行2。。。" + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
