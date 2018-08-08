package com.lris.ain.core.utils.parser;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class BigDecimalParser
{
  public static BigDecimal parse(String value)
  {
    if (StringUtils.isEmpty(value))
      return BigDecimal.valueOf(0L);
    try
    {
      return new BigDecimal(StringUtils.trim(value).replaceAll(",", "")); } catch (NumberFormatException e) {
    }
    return BigDecimal.valueOf(0L);
  }

  public static BigDecimal[] parseArray(String[] values)
  {
    if ((values == null) || (values.length == 0)) {
      return null;
    }
    BigDecimal[] ds = new BigDecimal[values.length];
    for (int i = 0; i < values.length; i++) {
      if (StringUtils.isEmpty(values[i]))
        ds[i] = BigDecimal.valueOf(0L);
      else
        try
        {
          ds[i] = new BigDecimal(StringUtils.trim(values[i]).replaceAll(",", ""));
        }
        catch (NumberFormatException e) {
          ds[i] = BigDecimal.valueOf(0L);
        }
    }
    return ds;
  }
}

