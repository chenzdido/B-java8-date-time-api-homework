package com.thoughtworks.capability.gtb;

import java.time.LocalDate;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

/**
 * 对任意日期获取下一个工作日, 不考虑节假日
 *
 * @author itutry
 * @create 2020-05-15_17:20
 */
public class Practice2 {

  public static LocalDate getNextWorkDate(LocalDate date) {
    if(date.getDayOfWeek().equals(FRIDAY)){
      return date.plusDays(3);
    }else if(date.getDayOfWeek().equals(SATURDAY)){
      return date.plusDays(2);
    }else{
      return date.plusDays(1);
    }
  }
}
