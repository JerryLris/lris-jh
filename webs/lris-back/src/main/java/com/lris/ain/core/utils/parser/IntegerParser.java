package com.lris.ain.core.utils.parser;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;


public class IntegerParser
{
  public static int parse(Object value)
  {
    return value == null ? 0 : parse(value.toString(), 0);
  }

  public static Integer parseObject(Object value) {
    return Integer.valueOf(value == null ? Integer.valueOf(0).intValue() : parse(value.toString(), 0));
  }

  public static int parse(String value) {
    return parse(value, 0);
  }

  public static int parse(String value, int defaultValue) {
    if (StringUtils.isEmpty(value))
      return defaultValue;
    try
    {
      return Integer.parseInt(StringUtils.trim(value).replaceAll(",", ""));
    } catch (NumberFormatException e) {
    }
    return defaultValue;
  }

  public static int[] parseArray(String[] values)
  {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    int[] fs = new int[values.length];
    for (int i = 0; i < values.length; i++) {
      try {
        fs[i] = Integer.parseInt(StringUtils.trim(values[i]).replaceAll(",", ""));
      }
      catch (NumberFormatException e) {
        fs[i] = 0;
      }
    }
    return fs;
  }

  public static Integer parseObject(String value) {
    return Integer.valueOf(parse(value));
  }

  public static Integer[] parseObjectArray(String[] values) {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    Integer[] fs = new Integer[values.length];
    for (int i = 0; i < values.length; i++) {
      try {
        fs[i] = Integer.valueOf(StringUtils.trim(values[i]).replaceAll(",", ""));
      }
      catch (NumberFormatException e) {
        fs[i] = Integer.valueOf(0);
      }
    }
    return fs;
  }
  
  /**
   * 对给定数目的自0开始步长为1的数字序列进行乱序
   * @param no 数组的长度
   * @return
   */
  public static int[] getSequence(int no) {
      int[] sequence = new int[no];
      for(int i = 0; i < no; i++){
          sequence[i] = i;
      }
      Random random = new Random();
      for(int i = 0; i < no; i++){
          int p = random.nextInt(no);
          int tmp = sequence[i];
          sequence[i] = sequence[p];
          sequence[p] = tmp;
      }
      random = null;
      return sequence;
  }
  
}