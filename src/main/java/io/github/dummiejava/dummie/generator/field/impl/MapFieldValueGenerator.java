package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapFieldValueGenerator extends FieldValueGenerator {

  public MapFieldValueGenerator() {
    super(Map.class);
  }

  // TODO: not support enum map
  private Map generateValue(Class<?> type) {
    Map value = null;
    if (super.isMatchType(type)) {
      value = new HashMap();
    } else {
      try {
        value = (Map) type.newInstance();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    return value;
  }

  @Override
  public Map generate(DataGenerator dataGenerator, Field field, GenerationStrategy strategy) {
    Map value = generateValue(field.getType());

    Type genericType = field.getGenericType();
    if (genericType instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) genericType;
      Class<?> keyClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
      Class<?> valueClass = (Class<?>) parameterizedType.getActualTypeArguments()[1];

      value.put(dataGenerator.getData(keyClass, generateKeyValue(strategy)),
          dataGenerator.getData(valueClass, generateKeyValue(strategy)));
    }

    return value;
  }

  @Override
  public boolean isMatchType(Class<?> targetFieldType) {
    return Map.class.isAssignableFrom(targetFieldType);
  }

  @Override
  public Map generate(DataGenerator dataGenerator, Class<?> fieldType, String fieldName, GenerationStrategy strategy) {
    return generateValue(fieldType);
  }
}
