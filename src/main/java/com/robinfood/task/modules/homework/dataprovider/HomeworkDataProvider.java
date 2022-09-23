/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.modules.homework.dataprovider;

import com.robinfood.task.domain.exception.FileErrorException;
import com.robinfood.task.domain.exception.HomeworkErrorException;
import com.robinfood.task.domain.exception.JsonErrorException;
import com.robinfood.task.domain.exception.generic.EMessageAplication;
import com.robinfood.task.domain.lasting.EInitial;
import com.robinfood.task.domain.models.DataUser;
import com.robinfood.task.domain.models.HomeworkDTO;
import com.robinfood.task.domain.persistence.entity.HomeworkEntity;
import com.robinfood.task.domain.persistence.entity.NoteEntity;
import com.robinfood.task.domain.persistence.entity.ProfessorEntity;
import com.robinfood.task.domain.persistence.entity.StudentEntity;
import com.robinfood.task.domain.persistence.entity.UnityEntity;
import com.robinfood.task.domain.persistence.repository.IHomeworkRepository;
import com.robinfood.task.domain.persistence.repository.INoteRepository;
import com.robinfood.task.domain.persistence.repository.IProfessorRepository;
import com.robinfood.task.domain.persistence.repository.IStudentRepository;
import com.robinfood.task.domain.persistence.repository.IUnityRepository;
import com.robinfood.task.domain.traslate.Translator;
import com.robinfood.task.domain.utils.FileUtil;
import com.robinfood.task.domain.utils.JsonUtil;
import com.robinfood.task.modules.homework.dataprovider.jpa.IJpaHomeworkProvider;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bobsuarez
 */
@Component
public class HomeworkDataProvider implements IJpaHomeworkProvider
{

  @Autowired
  private IHomeworkRepository homeworkRepository;

  @Autowired
  private IStudentRepository studentRepository;

  @Autowired
  private IProfessorRepository professorRepository;

  @Autowired
  private IUnityRepository rulesRepository;

  @Autowired
  private INoteRepository noteRepository;

  @Autowired
  @Qualifier("entityToHomeworkTraslate")
  private Translator<HomeworkEntity, HomeworkDTO> EntityToDtoTranslator;

  @Autowired
  @Qualifier("noteEntityToDtoTraslate")
  private Translator<NoteEntity, HomeworkDTO> noteEntityToDto;

  @Override
  public HomeworkDTO processFile(MultipartFile studentFile, String idStudent, String idProfessor)
          throws HomeworkErrorException, FileErrorException, JsonErrorException
  {
    StudentEntity findStudent = findStudentByIdentification(idStudent);
    ProfessorEntity findProfessor = findProfessorByIdentification(idProfessor);
    processValidDateLimit(findProfessor);
    var buildFileName = FileUtil.copyDirectoryOutResource(studentFile, "tareas", idStudent);
    HomeworkEntity homeworkToSave = HomeworkEntity.builder()
            .fkStudent(findStudent)
            .fileName(buildFileName)
            .instant(LocalDateTime.now())
            .status("A").build();
    return saveHomework(homeworkToSave);
  }

  @Override
  @Transactional
  public HomeworkDTO saveHomework(HomeworkEntity homeworkToSave)
  {
    HomeworkEntity findExitsFile = homeworkRepository
            .findByFileNameAndStatus(homeworkToSave.getFileName(), "A");
    if (findExitsFile != null) {
      findExitsFile.setStatus("D");
      homeworkRepository.save(findExitsFile);
    }
    var dataToSaved = homeworkRepository.save(homeworkToSave);
    return EntityToDtoTranslator.translate(dataToSaved);
  }

  @Override
  @Transactional(readOnly = true)
  public void processValidDateLimit(ProfessorEntity findProfessor)
          throws HomeworkErrorException, JsonErrorException
  {
    UnityEntity findDateLimit = rulesRepository
            .findByFkProfessorAndInitialAndStatus(findProfessor, EInitial.DATE_LIMIT.getCode(), "A");
    //Si no hay una fecha limite se deja seguir con la carga del archivo
    if (findDateLimit == null) {
      return;
    }
    Map<String, Object> parameters = JsonUtil.stringToHashMap(findDateLimit.getDescription());
    var dateLimit = LocalDate.parse(parameters.get("date").toString());
    var timeLimit = LocalTime.parse(parameters.get("time").toString());
    LocalDateTime buildDate = LocalDateTime.of(dateLimit, timeLimit);
    //Se valida si la fecha por el professor es posterior a la actual
    if (LocalDateTime.now().isAfter(buildDate)) {
      throw new HomeworkErrorException(EMessageAplication.DATE_LIMIT, buildDate);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public List<HomeworkDTO> findAllTaskList(String idStudent, LocalDate dateInitial, LocalDate dateFinal)
          throws HomeworkErrorException
  {
    List<HomeworkDTO> homeworkDto = new ArrayList<>();
    List<HomeworkEntity> homeworkList = homeworkRepository
            .findByTaskList(idStudent, dateInitial, dateFinal);
    if (!homeworkList.isEmpty()) {
      for (HomeworkEntity homerworkDB : homeworkList) {
        homeworkDto.add(EntityToDtoTranslator.translate(homerworkDB));
      }
    }
    return homeworkDto;
  }

  @Override
  @Transactional
  public HomeworkDTO updateNoteTask(Integer idTask, DataUser dataUser)
          throws HomeworkErrorException
  {
    var noteScore = dataUser.getValueClasification();
    if (noteScore < 0 || noteScore > 5) {
      throw new HomeworkErrorException(EMessageAplication.ERROR_NOTE_TASK);
    }
    Optional<HomeworkEntity> homeworkExist = homeworkRepository.findById(idTask);
    if (!homeworkExist.isPresent()) {
      throw new HomeworkErrorException(EMessageAplication.NOT_FOUND_TASK, idTask);
    }
    ProfessorEntity findProfessor = findProfessorByIdentification(dataUser.getIdProfessor());
    NoteEntity noteEntityToSaved = NoteEntity.builder()
            .fkHomework(homeworkExist.get())
            .fkProfessor(findProfessor)
            .valueNote(dataUser.getValueClasification())
            .instant(LocalDateTime.now())
            .status("A")
            .build();
    NoteEntity existNote = noteRepository.findByFkHomeworkAndStatus(homeworkExist.get(), "A");
    if (existNote != null) {
      existNote.setStatus("D");
      noteRepository.save(existNote);
    }
    var responseEntity = noteRepository.save(noteEntityToSaved);
    return noteEntityToDto.translate(responseEntity);
  }

  @Override
  @Transactional(rollbackFor = HomeworkErrorException.class)
  public void deteleTaskByCode(Integer codeTask)
          throws HomeworkErrorException
  {
    try {
      homeworkRepository.deleteById(codeTask);
    } catch (Exception e) {
      throw new HomeworkErrorException(EMessageAplication.ERROR_DELETE, codeTask);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public ProfessorEntity findProfessorByIdentification(String idProfessor) throws HomeworkErrorException
  {
    ProfessorEntity findProfessor = professorRepository.findByIdentification(idProfessor);
    if (findProfessor == null) {
      throw new HomeworkErrorException(EMessageAplication.NOT_FOUND_PROFESSOR, idProfessor);
    }
    return findProfessor;
  }

  @Override
  @Transactional(readOnly = true)
  public StudentEntity findStudentByIdentification(String idStudent)
          throws HomeworkErrorException
  {
    StudentEntity findStudent = studentRepository.findByIdentification(idStudent);
    if (findStudent == null) {
      throw new HomeworkErrorException(EMessageAplication.NOT_FOUND_STUDENT, idStudent);
    }
    return findStudent;
  }

}
