/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.traslate.homework;

import com.robinfood.task.domain.models.HomeworkDTO;
import com.robinfood.task.domain.persistence.entity.NoteEntity;
import com.robinfood.task.domain.traslate.Translator;
import org.springframework.stereotype.Component;

/**
 *
 * @author Bobsuarez
 */
@Component
public class NoteEntityToDtoTraslate implements Translator<NoteEntity, HomeworkDTO>
{
  @Override
  public HomeworkDTO translate(NoteEntity input)
  {
    var returnStatus = "A".equals(input.getFkHomework().getStatus()) ? "Último" : "Sobrescrito";
    return HomeworkDTO.builder()
            .codeHomework(input.getFkHomework().getCode())
            .identification(input.getFkHomework().getFkStudent().getFkInfoperson().getIdentification())
            .nameStudent(input.getFkHomework().getFkStudent().getFkInfoperson().getName())
            .lastNameStudent(input.getFkHomework().getFkStudent().getFkInfoperson().getLastName())
            .file(input.getFkHomework().getFileName())
            .instant(input.getFkHomework().getInstant())
            .status(returnStatus)
            .valueClasification(input.getValueNote())
            .build();
  }

}
