/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.robinfood.task.domain.exception;

import com.robinfood.task.domain.exception.generic.IGenericoMensaje;

/**
 *
 * @author coman
 */
public class AplicacionOKExcepcion extends Exception
{
  private static final long serialVersionUID = -1344640670884805385L;

  public static final String DESCRIPTION = "Se completo la operación";

  public AplicacionOKExcepcion()
  {
    super(DESCRIPTION);
  }

  public AplicacionOKExcepcion(IGenericoMensaje message, Object complement)
  {
    super(message.getMensaje().replaceAll("__COMPLEMENTO__", String.valueOf(complement)));
  }

  public AplicacionOKExcepcion(IGenericoMensaje message)
  {
    super(message.getMensaje());
  }
}
