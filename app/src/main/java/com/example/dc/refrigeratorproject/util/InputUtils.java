package com.example.dc.refrigeratorproject.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by DC on 2019/3/5.
 */

public class InputUtils {
    public static boolean isPhoneNumber(String input) {
        String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
        Pattern p = Pattern.compile (regex);
        return p.matches (regex, input);
    }

    public static String getRandomChar(int length) {            //生成随机字符串
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random random = new Random ();
        StringBuffer buffer = new StringBuffer ();
        for (int i = 0; i < length; i++) {
            buffer.append (chr[random.nextInt (36)]);
        }
        return buffer.toString ();
    }

    //常见的日期格式：
    //1，日期格式：String dateString = "2017-06-20 10:30:30" 对应的格式：String pattern = "yyyy-MM-dd HH:mm:ss";
    //2，日期格式：String dateString = "2017-06-20" 对应的格式：String pattern = "yyyy-MM-dd"
    //3，日期格式：String dateString = "2017年06月20日 10时30分30秒 对应的格式：String pattern = "yyyy年MM月dd日 HH时mm分ss秒";
    //4，日期格式：String dateString = "2017年06月20日" 对应的格式：String pattern = "yyyy年MM月dd日";

    public static long getStringToDate(String dateString, String pattern) {
        if (!TextUtils.isEmpty (dateString)){
            SimpleDateFormat dateFormat = new SimpleDateFormat (pattern);
            Date date = new Date ();
            try {
                date = dateFormat.parse (dateString);
            } catch (ParseException e) {
                e.printStackTrace ();
            }
            return date.getTime ();
        }
        return -1;

    }

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static long dateDiff(long endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        long startTime = getStringToDate (date,"yyyy-MM-dd");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = endTime
                    - startTime;
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            return day;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }



}
