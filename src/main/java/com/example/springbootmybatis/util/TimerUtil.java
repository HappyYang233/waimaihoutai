package com.example.springbootmybatis.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimerUtil {
    public static int getInitOrderStatus(){
        int status;
        SimpleDateFormat dateFormater = new SimpleDateFormat("HHmm");
        String date= dateFormater.format(new Date());
        System.out.println(date);
        int time = Integer.parseInt(date);
        if(time<1000 || (time>1300 && time<1600)){
             status=0;
        }else{
             status=1;
        }
        System.out.println("现在下单标志位是+"+status);
        return status;
    }
    public static int getInitOrderType(){
        int type;
        SimpleDateFormat dateFormater = new SimpleDateFormat("HHmm");
        String date= dateFormater.format(new Date());
        System.out.println(date);
        int time = Integer.parseInt(date);
        if(time<1300){
            type=0;
        }else{
            type=1;
        }
        System.out.println("现在用餐类型标志位是+"+type);
        return type;
    }
    public  static  Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
