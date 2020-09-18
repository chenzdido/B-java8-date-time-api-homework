package com.thoughtworks.capability.gtb;

import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 *   场景：
 *   a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 *   b:而我们在当前时区(北京时区)使用系统
 *   c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

  public static void main(String[] args) {
    String timeStr = "2020-04-01 14:30:00";

    // 根据格式创建格式化类
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 从字符串解析得到会议时间
    ZoneId zoneBjId = ZoneId.of("Asia/Shanghai");
    ZoneId zonChiId=ZoneId.of("America/Chicago");
    LocalDateTime meetingTime = LocalDateTime.parse(timeStr, formatter);
    ZonedDateTime bjMeetingTime = ZonedDateTime.of(meetingTime, zoneBjId);
    ZonedDateTime now = ZonedDateTime.now();
    if (now.isAfter(bjMeetingTime)) {
      ZonedDateTime tomorrow = now.plusDays(1);
      Period period = Period.between(bjMeetingTime.toLocalDate(),tomorrow.toLocalDate());
      bjMeetingTime = bjMeetingTime.plus(period);
      ZonedDateTime chiMeetingTime = bjMeetingTime.withZoneSameInstant(zonChiId);
      // 格式化新会议时间
      int offset = chiMeetingTime.getOffset().getTotalSeconds();
      String showTimeStr = formatter.format(chiMeetingTime.plusSeconds(offset));
      System.out.println(showTimeStr);
    } else {
      Period period = Period.between(now.toLocalDate(),bjMeetingTime.toLocalDate());
      System.out.println("会议还没开始呢");
      System.out.println("距离下次会议开始还有:" + period.getMonths() +
              " 月" + period.getDays() +
              " 天");
    }
  }
}
