/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.robinfood.task.domain.utils.LocalDateTimeDeserializer;
import com.robinfood.task.domain.utils.LocalDateTimeSerializer;
import java.time.LocalDateTime;
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
public class HomeworkDTO
{
  private int codeHomework;
  private String identification;
  private String nameStudent;
  private String lastNameStudent;
  private String file;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime instant;
  private int valueClasification;
  private String status;
}
