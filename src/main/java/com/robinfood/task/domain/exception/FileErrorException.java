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
public class FileErrorException extends Exception
{
  private static final long serialVersionUID = -1344640670995805385L;

  public static final String DESCRIPTION = "Error proceso archivo :";

  public FileErrorException()
  {
    super(DESCRIPTION);
  }

  public FileErrorException(String message)
  {
    super(DESCRIPTION + message);
  }

  public FileErrorException(IGenericoMensaje message, Throwable info)
  {
    super(DESCRIPTION + message.getMensaje(), info);
  }

  public FileErrorException(IGenericoMensaje message, String complement)
  {
    super(message.getMensaje().replaceAll("__COMPLEMENTO__", String.valueOf(complement)));
  }

  public FileErrorException(IGenericoMensaje message)
  {
    super(message.getMensaje());
  }

}
