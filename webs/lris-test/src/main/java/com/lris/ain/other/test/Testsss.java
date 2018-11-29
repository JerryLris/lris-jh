package com.lris.ain.other.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Testsss {

	public static void main(String[] args) {

        try{
 
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            String nowdate=format.format(new Date());//当前月份
 
            Date d1 = new SimpleDateFormat("yyyy-MM").parse("2018-01");//定义起始日期
 
            Date d2 = new SimpleDateFormat("yyyy-MM").parse("2018-01");//定义结束日期  可以去当前月也可以手动写日期。
 
            Calendar dd = Calendar.getInstance();//定义日期实例
 
            dd.setTime(d1);//设置日期起始时间
 
            while (dd.getTime().before(d2)) {//判断是否到结束日期
 
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
 
                String str = sdf.format(dd.getTime());
 
                System.out.println(str);//输出日期结果
 
                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
 
            }
 
        }catch (Exception e){
            System.out.println("异常"+e.getMessage());
        }
	}
	
	
}
