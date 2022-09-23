/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author BobSuarez
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataUser
{
  @JsonProperty("id_professor")
  public String idProfessor;
  @JsonProperty("value_clasification")
  public int valueClasification;

}
