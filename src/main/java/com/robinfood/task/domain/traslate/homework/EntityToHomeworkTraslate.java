/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.traslate.homework;

import com.robinfood.task.domain.models.HomeworkDTO;
import com.robinfood.task.domain.persistence.entity.HomeworkEntity;
import com.robinfood.task.domain.persistence.entity.NoteEntity;
import com.robinfood.task.domain.traslate.Translator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bobsuarez
 */
@Component
public class EntityToHomeworkTraslate implements Translator<HomeworkEntity, HomeworkDTO>
{
  @Override
  public HomeworkDTO translate(HomeworkEntity input)
  {
    var returnStatus = "A".equals(input.getStatus()) ? "Activo" : "Sobrescrito";
    int valueScore = 0;
    if (input.getNoteEntity() != null) {
      for (NoteEntity noteEntity : input.getNoteEntity()) {
        if (noteEntity.getStatus().equalsIgnoreCase("A")) {
          valueScore = noteEntity.getValueNote();
        }
      }
    }
    return HomeworkDTO.builder()
            .codeHomework(input.getCode())
            .identification(input.getFkStudent().getFkInfoperson().getIdentification())
            .nameStudent(input.getFkStudent().getFkInfoperson().getName())
            .lastNameStudent(input.getFkStudent().getFkInfoperson().getLastName())
            .file(input.getFileName())
            .instant(input.getInstant())
            .status(returnStatus)
            .valueClasification(valueScore)
            .build();
  }

}
