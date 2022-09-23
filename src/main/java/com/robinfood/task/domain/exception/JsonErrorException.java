/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.exception;

import com.robinfood.task.domain.exception.generic.IGenericoMensaje;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Bobsuarez
 */
@Getter
@Setter
public class JsonErrorException extends Exception
{
  private static final long serialVersionUID = -1344640670995805385L;

  public static final String DESCRIPTION = "Error proceso Json:";

  public JsonErrorException()
  {
    super(DESCRIPTION);
  }

  public JsonErrorException(String message)
  {
    super(DESCRIPTION + message);
  }

  public JsonErrorException(IGenericoMensaje message, Throwable info)
  {
    super(DESCRIPTION + message.getMensaje(), info);
  }

  public JsonErrorException(IGenericoMensaje message, String complement)
  {
    super(message.getMensaje().replaceAll("__COMPLEMENTO__", String.valueOf(complement)));
  }

  public JsonErrorException(IGenericoMensaje message)
  {
    super(message.getMensaje());
  }

}
