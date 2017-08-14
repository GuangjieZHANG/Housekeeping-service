package com.manage.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {
	public static Date getLastDayOfMonth(String sj){//得到指定日期当月最后一天
		Calendar cal = Calendar.getInstance();
		try{
		cal.setTime(getDateByString(sj));
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.roll(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();}
		catch(Exception e){
			return null;
		}
	}
	
	public static Date getDateByString(String sj){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date dt=sdf.parse(sj);
			return dt;
		}catch(Exception ex)
		{
			return null;
		}
	}
	
	public static Date getDateTimeByString(String sj){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date dt=sdf.parse(sj);
			return dt;
		}catch(Exception ex)
		{
			return null;
		}
	}
	
	public static double Hours(Timestamp start, Timestamp end) {
		return ((double)(end.getTime() - start.getTime()))/1000.0/60.0/60.0;
    }
	
	public static double formatDouble(double d, int p) {
        // 旧方法，已经不再推荐使用
		// BigDecimal bg = new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP);
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(p, RoundingMode.UP);
        return bg.doubleValue();
    }
	
    /**
     * 判断对象为空
     * 
     * @param obj
     *            对象名
     * @return 是否为空
     */
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }
	
	public static void main(int agrv,String[] agrs)
	{
		String a1 = "abc";
		String a2= "abc";
		System.out.println(a1==a2);
	}
	
}
