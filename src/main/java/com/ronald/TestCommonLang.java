package com.ronald;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class TestCommonLang {

    public static void main(String[] args) {

        TestCommonLang cl = new TestCommonLang();
        cl.testStringUtils();
        cl.testDateUtils();
    }

    /**
     * 测试StringUtils 相关方法
     */
    public void testStringUtils(){
        //左侧填充
        String s = StringUtils.leftPad("1221", 3, "0");
        System.out.println("s:" + s);

        //转小写
        String s1 = StringUtils.lowerCase("Abce");
        System.out.println(s1);

        //str2 不同于str1的部分
        String diff = StringUtils.difference("abc", "bc");

    }


    /**
     * DateUtils的使用
     */
    public void testDateUtils(){
        try {
            Date date1 = DateUtils.parseDate("2018/7", Locale.ENGLISH,
                    "yyyy-MM", "yyyy-M","yyyy/M", "yyyy-M-d", "yyyy-MM-dd", "yyyyMMdd", "yyyy/M/d",
                    "EEE MMM dd HH:mm:ss z yyyy");
            System.err.println("新日期：" + DateFormatUtils.format(date1, "yyyy-MM-dd"));
            long fragmentInDays = DateUtils.getFragmentInDays(date1, 1);
            System.err.println(fragmentInDays);

        } catch (ParseException e) {
            e.printStackTrace();
            System.err.println("日期格式错误");
        }
    }
}
