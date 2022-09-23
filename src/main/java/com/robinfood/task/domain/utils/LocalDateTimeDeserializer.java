/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robinfood.task.domain.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 *
 * @author
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>
{

  @Override
  public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
          throws IOException, JsonProcessingException
  {
    return DateUtil.parseFull(jsonParser.getValueAsString());

  }

}
