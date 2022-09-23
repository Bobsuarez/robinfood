/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robinfood.task.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.robinfood.task.domain.exception.JsonErrorException;
import com.robinfood.task.domain.exception.generic.EMessageAplication;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author spiwer
 */
public class JsonUtil
{

  private static final Logger LOG = Logger.getLogger(JsonUtil.class.getName());
  private static final JsonUtil jsonUtil = new JsonUtil();
  private ObjectMapper objectMapper;

  public static JsonUtil getInstance()
  {
    return jsonUtil;
  }

  public ObjectMapper getObjectMapper()
  {
    return objectMapper;
  }

  public void setObjectMapper(ObjectMapper objectMapper)
  {
    this.objectMapper = objectMapper;
  }

  public <T> List<T> parseToList(String json, Class<T> elementClass)
          throws JsonErrorException
  {
    try {
      CollectionType listType = objectMapper.getTypeFactory().
              constructCollectionType(List.class,
                      elementClass);
      return objectMapper.readValue(json,
              listType);
    } catch (JsonProcessingException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      throw new JsonErrorException(EMessageAplication.ERROR_PROPERTY_JSON, json);
    }
  }

  public <T> T parse(String json, Class<T> elementClass)
          throws JsonErrorException
  {
    try {
      return objectMapper.readValue(json, elementClass);
    } catch (JsonProcessingException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      throw new JsonErrorException(EMessageAplication.ERROR_PROPERTY_JSON, json);
    }
  }

  public String toJson(Object value)
  {
    try {
      return objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException ex) {
      LOG.log(Level.SEVERE, ex.getMessage(), ex);
      return "";
    }
  }

  public static Map<String, Object> stringToHashMap(String jsonStr)
          throws JsonErrorException
  {
    try {
      TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>()
      {
      };
      Map<String, Object> mapping = new ObjectMapper().readValue(jsonStr, typeRef);
      return mapping;
    } catch (JsonProcessingException e) {
      LOG.log(Level.SEVERE, e.getMessage(), e);
      throw new JsonErrorException(EMessageAplication.ERROR_PROPERTY_JSON);
    }
  }

}
