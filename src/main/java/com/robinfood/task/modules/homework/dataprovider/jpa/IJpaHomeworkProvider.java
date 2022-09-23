/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.robinfood.task.modules.homework.dataprovider.jpa;

import com.robinfood.task.domain.exception.FileErrorException;
import com.robinfood.task.domain.exception.HomeworkErrorException;
import com.robinfood.task.domain.exception.JsonErrorException;
import com.robinfood.task.domain.models.DataUser;
import com.robinfood.task.domain.models.HomeworkDTO;
import com.robinfood.task.domain.persistence.entity.HomeworkEntity;
import com.robinfood.task.domain.persistence.entity.ProfessorEntity;
import com.robinfood.task.domain.persistence.entity.StudentEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bobsuarez
 */
public interface IJpaHomeworkProvider
{
  HomeworkDTO processFile(MultipartFile arhivo,
          String idStudent, String idProfessor)
          throws HomeworkErrorException, FileErrorException, JsonErrorException;

  HomeworkDTO saveHomework(HomeworkEntity homeworkEntity);

  void processValidDateLimit(ProfessorEntity findProfessor)
          throws HomeworkErrorException, JsonErrorException;

  List<HomeworkDTO> findAllTaskList(String idStudent, LocalDate dateInitial, LocalDate dateFinal)
          throws HomeworkErrorException;

  void deteleTaskByCode(Integer codeTask)
          throws HomeworkErrorException;

  HomeworkDTO updateNoteTask(Integer idTask, DataUser dataUser)
          throws HomeworkErrorException;

  ProfessorEntity findProfessorByIdentification(String idProfessor)
          throws HomeworkErrorException;

  StudentEntity findStudentByIdentification(String idStudent)
          throws HomeworkErrorException;
}
