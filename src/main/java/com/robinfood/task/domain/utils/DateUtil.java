/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class Utilities
 *
 * @author RECEPCION
 */
@Slf4j
@Component
public class DateUtil
{

  /**
   * parseFull
   *
   * @param time input date
   * @return LocalDateTime
   */
  public static LocalDateTime parseFull(String time)
  {
    try {
      return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    } catch (Exception e) {
    }
    try {
      return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    } catch (Exception e) {
    }
    try {
      return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
    } catch (Exception e) {
    }
    try {
      return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0000"));
    } catch (Exception e) {
    }
    try {
      return LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
    } catch (Exception e) {
    }
    return LocalDateTime.parse(time);
  }

  /**
   * parseToString
   *
   * @param date input date
   * @return String
   */
  public String parseToString(LocalDateTime date)
  {
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSSSSS Z");
    return date.format(formatDate);
  }

  public static String formatStandard(LocalDateTime date)
  {
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return date.format(formatDate);
  }

  /**
   * Utility used to convert a localDate to Date
   *
   * @param localDate to convert
   * @return Date
   */
  public Date parse(LocalDate localDate)
  {
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateFormat = formatter.format(date);
    Date resulDate = null;
    try {
      resulDate = formatter.parse(dateFormat);
    } catch (ParseException ex) {
      Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
    return resulDate;
  }

  /**
   * Utility used to convert a LocalDateTime to Date
   *
   * @param localDateTime to convert
   * @return Date
   */
  public Date parse(LocalDateTime localDateTime)
  {
    Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateFormat = formatter.format(date);
    Date resulDate = null;
    try {
      resulDate = formatter.parse(dateFormat);
    } catch (ParseException ex) {
      Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
    }
    return resulDate;
  }

  /**
   * Utility used to convert a String to Date
   *
   * @param localDateTime to convert
   * @return Date
   */
  public Date parse(String localDateTime)
  {
    LocalDateTime dateTime = LocalDateTime.parse(localDateTime);
    return parse(dateTime);
  }

}
