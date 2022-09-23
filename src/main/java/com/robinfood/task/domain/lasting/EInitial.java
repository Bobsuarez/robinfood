/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.robinfood.task.domain.lasting;

import com.robinfood.task.domain.lasting.interfaces.IGenericValue;
import lombok.AllArgsConstructor;

/**
 *
 * @author BobSuarez
 */
@AllArgsConstructor
public enum EInitial implements IGenericValue
{

  DATE_LIMIT("date_limit", "DL", "fecha limite de entrega"),
  NOTES("notes", "NT", "lista de notas "),
  RULES("rules", "RL", "criterios para calificar");

  private final String title;
  private final String code;
  private final String description;

  @Override
  public String getCode()
  {
    return this.code;
  }

  @Override
  public String getTitle()
  {
    return this.title;
  }

  @Override
  public String getDescription()
  {
    return this.description;
  }

}
