package com.lris.ain.core.utils.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

public class SQLDateParser
{
  protected static final String DEFAULT_PATTERN = "yyyy-MM-dd";

  public static java.sql.Date parse(String value)
  {
    return parse(value, "yyyy-MM-dd");
  }

  public static java.sql.Date parse(String value, String pattern)
  {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    if (StringUtils.isEmpty(pattern)) {
      pattern = "yyyy-MM-dd";
    }
    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
    try {
      return new java.sql.Date(fmt.parse(value).getTime()); } catch (ParseException e) {
    }
    return null;
  }

  public static java.sql.Date[] parseArray(String[] values)
  {
    return parseArray(values, "yyyy-MM-dd");
  }

  public static java.sql.Date[] parseArray(String[] values, String pattern) {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    if (StringUtils.isEmpty(pattern)) {
      pattern = "yyyy-MM-dd";
    }
    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
    java.sql.Date[] dates = new java.sql.Date[values.length];
    for (int i = 0; i < values.length; i++) {
      if (StringUtils.isEmpty(values[i]))
        continue;
      try
      {
        dates[i] = new java.sql.Date(fmt.parse(values[i]).getTime());
      } catch (ParseException e) {
      }
    }
    return dates;
  }

  public static String format(java.sql.Date date) {
    return format(date, "yyyy-MM-dd");
  }

  public static String format(java.sql.Date date, String pattern) {
    if (date == null) {
      return "";
    }
    if (StringUtils.isEmpty(pattern)) {
      pattern = "yyyy-MM-dd";
    }
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    try {
      return format.format(date); } catch (IllegalArgumentException e) {
    }
    return "";
  }
}

