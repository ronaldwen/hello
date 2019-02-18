package com.ronald;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Calendar;

public class TestDate8 {

    private LocalDate now = LocalDate.now();
    private LocalTime nowT = LocalTime.now();
    private LocalDateTime nowDt = LocalDateTime.now();

    public static void main(String[] args) {

        TestDate8 m = new TestDate8();

        m.getMs(LocalDate.now());
        m.get();

        m.getCurDate();
        m.format();
        m.parse();
    }

    /**
     *
     * @param date
     * @return
     */
    public long getMs(LocalDate date){
        long l = date.getLong(ChronoField.DAY_OF_MONTH);
        System.out.println("当月的第【" + l + "】天");
        return l;
    }


    public void get(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long timeInMillis = cal.getTimeInMillis();
        System.out.println(timeInMillis);
    }

    /**
     * 取当前日期
     * @return
     */
    public void getCurDate(){
        System.out.println(now);
        System.out.println(nowT);
        System.out.println(nowDt);
    }

    /**
     * 格式化为字符串
     */
    public void format(){
        String nowStr = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        String nowStr2 = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        String nowStr3 = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        String nowStr4 = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

        System.out.println(nowStr);
        System.out.println(nowStr3);
        System.out.println(nowStr4);
        //下午10:59
        String format2 = nowT.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
        System.out.println(format2);
        //2016-5-27 22:59:10
        String format = nowDt.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        System.out.println(format);

        String format3 = nowDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format3);
        String format4 = nowDt.format(DateTimeFormatter.ofPattern("yyyy年M月dd HH:mm:ss"));
        System.out.println(format4);
    }


    /**
     * 解析字符串为日期时间对象
     */
    private void parse(){
        LocalDate date = LocalDate.parse("2016-05-26");
        System.out.println(date.toString());

        LocalDate date2 = LocalDate.parse("2016-06-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date2.toString());
    }
}
