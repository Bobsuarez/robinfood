/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.modules.homework.api;

import com.robinfood.task.domain.exception.ApiExceptionHandler;
import com.robinfood.task.domain.exception.FileErrorException;
import com.robinfood.task.domain.exception.HomeworkErrorException;
import com.robinfood.task.domain.exception.JsonErrorException;
import com.robinfood.task.domain.models.DataUser;
import com.robinfood.task.domain.models.HomeworkDTO;
import com.robinfood.task.modules.homework.usercase.HomeworkService;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bobsuarez
 */
@RestController
@RequestMapping(
        value = "/homework",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
public class HomeworkController extends ApiExceptionHandler
{
  @Autowired
  private HomeworkService service;

  public final String ID_PATH = "/{id}";

  @PostMapping("/task-upload")
  public ResponseEntity<HomeworkDTO> saveFileTask(
          @RequestParam("archivo") MultipartFile archivo,
          @RequestParam("id_student") String idStudent,
          @RequestParam("id_professor") String idProfessor)
          throws HomeworkErrorException, FileErrorException, JsonErrorException
  {
    var responseService = service.saveFileTask(archivo, idStudent, idProfessor);
    return new ResponseEntity<>(responseService, HttpStatus.CREATED);
  }

  @GetMapping(value = "/list", params = {"id", "date_initial", "date_final"})
  public ResponseEntity<List<HomeworkDTO>> getFileTaskList(
          @RequestParam("id") String idStudent,
          @RequestParam("date_initial")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateInitial,
          @RequestParam("date_final")
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinal)
          throws HomeworkErrorException, FileErrorException, JsonErrorException
  {
    var responseService = service.findAllTaskList(idStudent, dateInitial, dateFinal);
    return new ResponseEntity<>(responseService, HttpStatus.CREATED);
  }

  @DeleteMapping(ID_PATH)
  public ResponseEntity<HttpStatus> deleteTaskByCode(
          @PathVariable("id") @Valid Integer codeTask)
          throws HomeworkErrorException
  {
    service.deteleTaskByCode(codeTask);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping(value = ID_PATH)
  public ResponseEntity<HomeworkDTO> UpdateNoteTask(
          @PathVariable("id") @Valid int idTask,
          @RequestBody @Valid DataUser dataUser)
          throws HomeworkErrorException
  {
    var responseService = service.updateNoteTask(idTask, dataUser);
    return new ResponseEntity<>(responseService, HttpStatus.CREATED);
  }

}
