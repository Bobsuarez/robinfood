/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.modules.homework.usercase;

import com.robinfood.task.domain.exception.FileErrorException;
import com.robinfood.task.domain.exception.HomeworkErrorException;
import com.robinfood.task.domain.exception.JsonErrorException;
import com.robinfood.task.domain.models.DataUser;
import com.robinfood.task.domain.models.HomeworkDTO;
import com.robinfood.task.modules.homework.dataprovider.jpa.IJpaHomeworkProvider;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bobsuarez
 */
@Service
public class HomeworkService
{

  @Autowired
  private IJpaHomeworkProvider provider;

  public HomeworkDTO saveFileTask(MultipartFile arhivo,
          String idStudent, String idProfessor)
          throws HomeworkErrorException, FileErrorException, JsonErrorException
  {
    try {
      return this.provider.processFile(arhivo, idStudent, idProfessor);
    } catch (HomeworkErrorException e) {
      throw new HomeworkErrorException(e.getMessage());
    }
  }

  public List<HomeworkDTO> findAllTaskList(String idStudent, LocalDate dateInitial, LocalDate dateFinal)
          throws HomeworkErrorException, FileErrorException, JsonErrorException
  {
    try {
      return this.provider.findAllTaskList(idStudent, dateInitial, dateFinal);
    } catch (HomeworkErrorException e) {
      throw new HomeworkErrorException(e.getMessage());
    }
  }

  public void deteleTaskByCode(Integer codeTask)
          throws HomeworkErrorException
  {
    try {
      this.provider.deteleTaskByCode(codeTask);
    } catch (HomeworkErrorException e) {
      throw new HomeworkErrorException(e.getMessage());
    }
  }

  public HomeworkDTO updateNoteTask(Integer idTask, DataUser dataUser)
          throws HomeworkErrorException
  {
    try {
      return this.provider.updateNoteTask(idTask, dataUser);
    } catch (HomeworkErrorException e) {
      throw new HomeworkErrorException(e.getMessage());
    }
  }

}
