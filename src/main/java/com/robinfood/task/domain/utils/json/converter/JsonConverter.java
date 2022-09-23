/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.utils.json.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PGobject;

/**
 *
 * @author BobSuarez
 */
@Slf4j
@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<Map<String, ? extends Object>, PGobject>
{
  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public PGobject convertToDatabaseColumn(Map<String, ? extends Object> map)
  {
    PGobject po = new PGobject();
    po.setType("jsonb");

    try {
      po.setValue(map == null ? null : MAPPER.writeValueAsString(map));
    } catch (SQLException | JsonProcessingException ex) {
      log.error("Cannot convert JsonObject to PGobject.");
      throw new IllegalStateException(ex);
    }
    return po;

  }

  @Override
  public Map<String, ? extends Object> convertToEntityAttribute(PGobject dbData)
  {
    if (dbData == null || dbData.getValue() == null) {
      return null;
    }
    try {
      return MAPPER.readValue(dbData.getValue(), HashMap.class);
    } catch (JsonProcessingException ex) {
      log.error(ex.getMessage());
      return null;
    }
  }

}
