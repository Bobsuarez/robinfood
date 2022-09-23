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
 * @param <T>
 */
@Getter
@Setter
public class ErrorMessage<T extends Object>
{

  private String exception;
  private String message;
  private String path;
  private Object info;

  public ErrorMessage()
  {
    this.message = "OK";
  }

  public ErrorMessage(String exception, String message, String path, Object info)
  {
    this.exception = exception;
    this.message = message;
    this.path = path;
    this.info = info;
  }

  public ErrorMessage(IGenericoMensaje iMessage)
  {
    this.message = iMessage.getMensaje();
  }

  public ErrorMessage(Exception exception, String path)
  {
    this(exception.getClass().getSimpleName(), exception.getMessage(), path, null);
  }

  public ErrorMessage(Exception exception, String path, T info)
  {
    this(exception.getClass().getSimpleName(), exception.getMessage(), path, info);
  }

  public ErrorMessage(String exception, String path, T info)
  {
    this(exception, exception, path, info);
  }

}
