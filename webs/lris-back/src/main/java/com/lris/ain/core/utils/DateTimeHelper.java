package com.lris.ain.core.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import org.joda.time.DateTime;

import com.lris.ain.core.exception.ParamException;

/**
 * 日期时间工具类
 * 
 * @author tom
 * @date 2015年5月7日 上午9:52:31
 */
public class DateTimeHelper {

	/**
	 * 日期格式,格式：yyyy-MM-dd
	 */
	public static String NOW_DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 时间格式，精确到分钟,格式：yyyy-MM-dd HH:mm
	 */
	public static String NOW_MIN_FORMAT = "yyyy-MM-dd HH:mm";
	/**
	 * 时间格式，精确到秒,格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String NOW_SEC_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 时间格式，精确到毫秒,格式：yyyy-MM-dd HH:mm:ss:SSS
	 */
	public static String NOW_MIC_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
	/**
	 * 时间格式，精确到秒,格式：yyyyMMddHHmmss
	 */
	public static String NOW_SEC_FORMAT_NO_SYMBOL = "yyyyMMddHHmmss";

	public static final long DAY_IN_MILLISECONDS = 86400000L;

	/**
	 * 根据给定的格式来获取日期和时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getDatetime(String format) {
		DateTime now = new DateTime();
		return now.toString(format);
	}

	/**
	 * 获取sql date 的当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static Date getSqlNow(String format) {
		return Date.valueOf(getDatetime(format));
	}

	public static Timestamp getSqlTimeNow(String format) {
		return Timestamp.valueOf(getDatetime(format));
	}

	public static Date rollSqlDay(Date date, int rolldays) {
		return new Date(date.getTime() + rolldays * 86400000L);
	}

	public static Date rollSqlMonth(Date date, int months) {
		if (months == 0) {
			return date;
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.add(Calendar.MONTH, months);
		return new Date(c.getTimeInMillis());
	}

	public static long dateSqlDiff(Date date1, Date date2) {
		return date2.getTime() - date1.getTime();
	}

	/**
	 * 根据给定的格式来获取日期和时间，以数字的格式返回
	 * 
	 * @param format
	 * @return
	 */
	public static long getDatetimeNum(String format) {
		long time = 0;
		String now = getDatetime(format);
		time = Long.parseLong(now.replaceAll("-", "").replaceAll(" ", "")
				.replaceAll(":", ""));
		return time;
	}

	public static String format(String format, Date date) {
		DateTime dateTime = new DateTime(date.getTime());
		return dateTime.toString(format);
	}

	public static String format(String format, Timestamp timestamp) {
		DateTime dateTime = new DateTime(timestamp.getTime());
		return dateTime.toString(format);
	}
	
	/**
	 * 转换秒数为%天%小时%分%秒
	 * @author hulipeng
	 * @param sec
	 * @return
	 */
	public static String formatSec(String sec) {
		if(sec==null ||"".equals(sec) || sec.equals("0"))
			return "0秒";
		Long d=0l,h=0l,m=0l,s=0l;
		Long l = new Long(sec);
		if(l<60){//秒
			s=l;
		}else if(l>=60 && l<3600){//分钟
			m=l/60;
			s=l-m*60;
		}else if(l>=3600 && l<86400){//小时
			h=l/3600;
			m=(l-h*3600)/60;
			s=l-h*3600-m*60;
		}else{//天数
			d=l/86400;
			h=(l-86400*d)/3600;
			m=(l-d*86400-h*3600)/60;
			s=l-d*86400-h*3600-m*60;
		}
		String ds = "",hs = "",ms = "",ss = "";
		if(d!=0)
			ds=d.toString()+"天";
		if(h!=0)
			hs=h.toString()+"小时";
		if(m!=0)
			ms=m.toString()+"分";
		if(s!=0)
			ss=s.toString()+"秒";
		return ds+hs+ms+ss;
	}

	public static long dateSqlDiff(Timestamp date1, Timestamp date2) {
		return date2.getTime() - date1.getTime();
	}
	
	public static int dateSqlDiffDays(Date date1,Date date2){
		return (int)((date2.getTime()-date1.getTime())/86400000L);
	}
	/**
	 * 获取当月的第一天和最后一天
	 * @param isStart
	 * @return
	 */
	public static Date getMonthStartOrEnd(boolean isStart) {
		return getDateMonthStartOrEnd(null,isStart);
	}
	
	/**
	 * 获取某个时间的第一天和最后一天
	 * @param isStart
	 * @return
	 */
	public static Date getDateMonthStartOrEnd(Date date,boolean isStart) {
		Calendar cal = Calendar.getInstance();
		if(date==null) {
			cal.setTime(getSqlNow(NOW_DATE_FORMAT));
		}else {
			cal.setTime(date); 
		}
		if(isStart){
			cal.set(Calendar.DAY_OF_MONTH, 1); 
			return new Date(cal.getTimeInMillis());
		}else{
			cal.set(Calendar.DATE, 1);
			cal.roll(Calendar.DATE, -1);
			return new Date(cal.getTimeInMillis());
		}
	}
	
	/**
	 * 根据年月获取对应月份的天数
	 * @param year
	 * @param month
	 * @return
	 */
	 public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);
        return maxDate;  
	 }
	 
	 /**
	  * 获取年龄
	  * @param birthDay
	  * @return
	  * @throws Exception
	  */
	 public static int getAge(Date birthDay) throws Exception { 
		Calendar cal = Calendar.getInstance(); 
        if (cal.before(birthDay)) { 
            throw new ParamException( 
                "The birthDay is before Now.It's unbelievable!"); 
        } 
        int yearNow = cal.get(Calendar.YEAR); 
        int monthNow = cal.get(Calendar.MONTH); 
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 
        cal.setTime(birthDay); 
  
        int yearBirth = cal.get(Calendar.YEAR); 
        int monthBirth = cal.get(Calendar.MONTH); 
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 
  
        int age = yearNow - yearBirth; 
  
        if (monthNow <= monthBirth) { 
            if (monthNow == monthBirth) { 
                if (dayOfMonthNow <= dayOfMonthBirth) age--; 
            }else{ 
                age--; 
            } 
        } 
        return age; 
    }
	 /**
	  * 获取两时间距离（多少年多少月多少日）
	  * @param from2
	  * @param now
	  * @return
	  */
	 public static String workDateDiff(java.util.Date from2,java.util.Date now){
		Calendar  from  =  Calendar.getInstance();
		from.setTime(from2);
		Calendar  to  =  Calendar.getInstance();
		to.setTime(now);
		int fromYear = from.get(Calendar.YEAR);
		int fromMonth = from.get(Calendar.MONTH);
	    int fromDay = from.get(Calendar.DAY_OF_MONTH);
	    int toYear = to.get(Calendar.YEAR);
	    int toMonth = to.get(Calendar.MONTH);
	    int toDay = to.get(Calendar.DAY_OF_MONTH);
	    if(toDay<fromDay) {
	    	toMonth = toMonth - 1;
	    	to.add(Calendar.MONTH, -1);
	    	toDay = toDay + to.getActualMaximum(Calendar.DAY_OF_MONTH);
	    }
	    int day = toDay  - fromDay;
	    if(toMonth<fromMonth) {
	    	toYear = toYear - 1;
	    	to.set(Calendar.YEAR, toYear);
	    	toMonth = toMonth + 12;
	    }
	    int month = toMonth  - fromMonth;
	    int year = toYear  -  fromYear;
	    return year+"年"+month+"月"+day+"日";
	 }

}
