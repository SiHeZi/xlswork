package com.sj.work.tool.util;

import java.util.Calendar;

/**
 * Created by zhaosiji on 2017/8/2.
 */
public class DataUtil {


    /**
     * 获取星期几 周一即为1
     */
    public static int getWeedId(String date) {

        if (date == null) {

            return -1;
        }

        String[] data = date.split("-");

        if (date.length() < 3) {

            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(data[0]));// 年份
        calendar.set(Calendar.MONTH, Integer.parseInt(data[1]) - 1);// 月 1月为0
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));// 日
        // 日历中1代表周日
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // 周日
        if (dayOfWeek == 1) {

            return 7;
        } else {
            return dayOfWeek - 1;
        }

    }


    /**
     * 比较时间大小
     */
    public static boolean isCompare(String time,String compareFlag){
         String t=time.split(" ")[1].split(":")[0];

         int t1=Integer.valueOf(t);
         int t2=Integer.valueOf(compareFlag.split(":")[0]);

         //上午
         if(t1<t2){
            return true;
         }else {
             return false;
         }

    }

}
