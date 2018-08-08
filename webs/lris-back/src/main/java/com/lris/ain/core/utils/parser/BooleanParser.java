package com.lris.ain.core.utils.parser;

import org.apache.commons.lang3.StringUtils;


public class BooleanParser
{
  public static boolean parse(String value)
  {
    if (StringUtils.isEmpty(value)) {
      return false;
    }
    value = value.trim();

    return ("Y".equalsIgnoreCase(value)) || ("1".equalsIgnoreCase(value)) || ("true".equalsIgnoreCase(value)) || ("yes".equalsIgnoreCase(value));
  }

  public static boolean[] parseArray(String[] values)
  {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    boolean[] b = new boolean[values.length];
    for (int i = 0; i < values.length; i++) {
      b[i] = parse(values[i]);
    }
    return b;
  }

  public static Boolean parseObject(String value) {
    if (StringUtils.isEmpty(value)) {
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(parse(value));
  }

  public static Boolean[] parseObjectArray(String[] values) {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    Boolean[] b = new Boolean[values.length];
    for (int i = 0; i < values.length; i++) {
      b[i] = Boolean.valueOf(parse(values[i]));
    }
    return b;
  }
}

