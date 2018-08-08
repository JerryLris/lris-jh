package com.lris.ain.core.utils.parser;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class TimestampParser
{
  protected static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
  protected static final String DEFAULT_PATTERN_1 = "yyyy-MM-dd";
  protected static final String DEFAULT_PATTERN_2 = "yyyy-MM-dd HH";
  protected static final String DEFAULT_PATTERN_3 = "yyyy-MM-dd HH:mm";

  public static Timestamp parse(String value)
  {
    return parse(value, "yyyy-MM-dd HH:mm:ss");
  }

  public static Timestamp parse(String value, String pattern) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    if ((StringUtils.isEmpty(pattern)) || (value.length() == 19))
      pattern = "yyyy-MM-dd HH:mm:ss";
    else if (value.length() == 16)
      pattern = "yyyy-MM-dd HH:mm";
    else if (value.length() == 13)
      pattern = "yyyy-MM-dd HH";
    else if (value.length() == 10) {
      pattern = "yyyy-MM-dd";
    }
    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
    try {
      return new Timestamp(fmt.parse(value).getTime()); } catch (ParseException e) {
    }
    return null;
  }

  public static Timestamp[] parseArray(String[] values)
  {
    return parseArray(values, "yyyy-MM-dd HH:mm:ss");
  }

  public static void main(String[] args) {
    String value = "yyyy-MM-dd HH:mm:ss";
    System.out.println(value.length());
  }

  public static Timestamp[] parseArray(String[] values, String pattern) {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    if (StringUtils.isEmpty(pattern)) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }
    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
    Timestamp[] dates = new Timestamp[values.length];
    for (int i = 0; i < values.length; i++) {
      if (StringUtils.isEmpty(values[i]))
        continue;
      try
      {
        dates[i] = new Timestamp(fmt.parse(values[i]).getTime());
      } catch (ParseException e) {
        dates[i] = null;
      }
    }
    return dates;
  }

  public static String format(Date date) {
    return format(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static String format(Date date, String pattern) {
    if (date == null) {
      return "";
    }
    if (StringUtils.isEmpty(pattern)) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    try {
      return format.format(date); } catch (IllegalArgumentException e) {
    }
    return "";
  }
}

