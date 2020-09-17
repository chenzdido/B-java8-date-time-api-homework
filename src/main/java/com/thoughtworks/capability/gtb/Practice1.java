package com.thoughtworks.capability.gtb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * 计算任意日期与下一个劳动节相差多少天
 *
 * @author itutry
 * @create 2020-05-15_16:33
 */
public class Practice1 {

  public static long getDaysBetweenNextLaborDay(LocalDate date) {
    LocalDate mayDay = LocalDate.of(date.getYear(), 5, 1);
    if(date.getMonth().getValue()>=5){
      mayDay = mayDay.plusYears(1);
    }
    return DAYS.between(date,mayDay);
  }
}
