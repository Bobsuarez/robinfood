/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Bobsuarez
 */
@ControllerAdvice
public class ApiExceptionHandler
{

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({Exception.class})
  @ResponseBody
  public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception)
  {
    return new ErrorMessage(exception, request.getRequestURI());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    HomeworkErrorException.class, FileErrorException.class})
  @ResponseBody
  public ErrorMessage badRequest(Exception exception)
  {
    var info = exception.getCause() == null ? "" : exception.getCause().getMessage();
    return new ErrorMessage(exception, "", info);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @ExceptionHandler({
    AplicacionOKExcepcion.class
  })
  @ResponseBody
  public ErrorMessage ok(AplicacionOKExcepcion ex)
  {
    return new ErrorMessage(ex, "");
  }
}
