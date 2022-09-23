/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.exception;

import com.robinfood.task.domain.exception.generic.IGenericoMensaje;

/**
 *
 * @author Bobsuarez
 */
public class HomeworkErrorException extends Exception
{
  private static final long serialVersionUID = -1344640719884805385L;

  public static final String DESCRIPTION = "Error proceso user :";

  public HomeworkErrorException()
  {
    super(DESCRIPTION);
  }

  public HomeworkErrorException(String message)
  {
    super(DESCRIPTION + message);
  }

  public HomeworkErrorException(IGenericoMensaje message, Throwable info)
  {
    super(DESCRIPTION + message.getMensaje(), info);
  }

  public HomeworkErrorException(IGenericoMensaje message, Object complement)
  {
    super(message.getMensaje().replaceAll("__COMPLEMENTO__", String.valueOf(complement)));
  }

  public HomeworkErrorException(IGenericoMensaje message)
  {
    super(message.getMensaje());
  }

}
