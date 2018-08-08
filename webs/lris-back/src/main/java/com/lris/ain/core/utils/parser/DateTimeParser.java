package com.lris.ain.core.utils.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateTimeParser
{
  protected static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

  public static Date parse(String value)
  {
    return parse(value, "yyyy-MM-dd HH:mm:ss");
  }

  public static Date parse(String value, String pattern)
  {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    if (StringUtils.isEmpty(pattern)) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }
    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
    try {
      return fmt.parse(value); } catch (ParseException e) {
    }
    return null;
  }

  public static Date[] parseArray(String[] values)
  {
    return parseArray(values, "yyyy-MM-dd HH:mm:ss");
  }

  public static Date[] parseArray(String[] values, String pattern) {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    if (StringUtils.isEmpty(pattern)) {
      pattern = "yyyy-MM-dd HH:mm:ss";
    }
    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
    Date[] dates = new Date[values.length];
    for (int i = 0; i < values.length; i++) {
      if (StringUtils.isEmpty(values[i]))
        continue;
      try
      {
        dates[i] = fmt.parse(values[i]);
      } catch (ParseException e) {
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

