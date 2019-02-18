package com.ronald;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();

//        m.testDateUtils();
//        m.testForExce();
//        m.testUrlEncode();
//        m.testDecimalFormat();
//        m.testDateOver("2018-09-33");
//        m.validIDCard("430423199804313618");
//        m.numberFormat();
        m.testBigDecimal();
    }


    public void test() {
        List<String> l = new ArrayList();
        for (String s : l) {
            System.out.println(s);
        }
    }


    public void testUrlEncode(){
        String enc = "UTF-8";
        String encodeStr = null;
        String url = "http://192.168.100.122:9000/getVersion?";
        try {
            encodeStr = URLEncoder.encode("user=张三丰", enc);

            System.out.println(URLEncoder.encode(url, enc));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(url + encodeStr);

        try {
            String decode = URLDecoder.decode(encodeStr, enc);
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    public void testForExce(){

        for (int i = 0 ; i < 10 ; i++){
            try {

                float a = i/(i -5);
                System.out.println(i);
            } catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }


    public void testDecimalFormat(){
        //模式中使用0 ,位数不足将补0， 使用#不会补足
        String d = new DecimalFormat("0.0000").format(0.44);
        System.out.println(d);

        //以百分比形式
        String d2 = new DecimalFormat("0.##%").format(11434/2700*1.0);
        System.out.println(d2);

        //HALF_UP 四舍五入
        BigDecimal bd = new BigDecimal(0.504);
        BigDecimal a = bd.setScale(2, RoundingMode.HALF_UP);
        System.out.println("默认：" + a.toString());
        System.out.println("去除末尾0,但是科学计数法：" + a.stripTrailingZeros().toString());
        System.out.println("去除末尾0："
                + new BigDecimal(0.000).stripTrailingZeros().toPlainString());
    }

    /**
     * 判断一个日期字符串是否超过当月日期
     * @param dateStr
     * @return
     */
    public boolean testDateOver(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(dateStr);
            String format = sdf.format(parse);
            boolean equals = dateStr.substring(0, 7).equals(format.substring(0, 7));
            System.err.println(equals);
            return equals;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    //验证身份证有效性
    private boolean validIDCard(String idCard) {
        boolean flag;
//		Pattern p = Pattern.compile("");
        String reg = "[1-9]\\d{5}(19|20|21)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]";
        flag = Pattern.matches(reg, idCard);
        System.err.println("idCard: " + (flag ? "有效" : "无效"));
        return flag;
    }


    /**
     * 数字格式化测试
     */
    public void numberFormat(){

        long l = 11;
        int d = 9/60;
        System.out.println(d);

//        String s = String.format("%.2f", d);
        // System.out.println(s);

        DecimalFormat df = new DecimalFormat(".#");
        System.out.println(df.format(24235233.1260));
    }

    /**
     * 测试BigDecimal
     */
    public void testBigDecimal(){
        BigDecimal big1 = new BigDecimal(20);
        BigDecimal big2 = new BigDecimal(4.2);
        int i = big1.divide(big2, BigDecimal.ROUND_UP).intValue();
        System.out.println(i);
    }

}
