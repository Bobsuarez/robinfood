/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.robinfood.task.domain.exception.generic;

import lombok.AllArgsConstructor;

/**
 *
 * @author coman
 */
@AllArgsConstructor
public enum EMessageAplication implements IGenericoMensaje
{

  OK("Petición ejecutada correctamente"),
  NO_RESULTS("No hay resultados"),
  NO_FOUND_DEBT("No una deuda vigente para el usuario __COMPLEMENTO__"),
  NO_FOUND_CLIENT("No existen una cuenta para el usuario __COMPLEMENTO__"),
  PAYMENT_OK("El valor de la deuda esta cancelada para el usuario __COMPLEMENTO__ "),
  PAYMENT_DEBT("El pago de cuota es mayor a la deuda "),
  FILE_BUILD("No se pudo almacenar"),
  ERROR_VALIDACION_MENSAJE("__COMPLEMENTO__"),
  NOT_FOUND_STUDENT("No existe el estudiante con identificación : __COMPLEMENTO__"),
  ERROR_PROPERTY_JSON("No se pudo realizar la conversión del json"),
  NOT_FOUND_PROFESSOR("El profesor a calificar no existe idententificación : __COMPLEMENTO__"),
  DATE_LIMIT("No es posible cargar la tarea, la fecha ha expirado __COMPLEMENTO__ "),
  ERROR_DELETE("Error al eliminar la tarea con id:  __COMPLEMENTO__ "),
  ERROR_NOTE_TASK(" La nota no es admitida por el sistema"),
  NOT_FOUND_TASK("El archivo tarea no existe id: __COMPLEMENTO__ ");

  private final String message;

  @Override
  public String getMensaje()
  {
    return message;
  }

}
