/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robinfood.task.domain.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>
{

  @Override
  public void serialize(LocalDateTime t, JsonGenerator jg, SerializerProvider sp)
          throws IOException
  {
    jg.writeString(DateUtil.formatStandard(t));
  }

}
