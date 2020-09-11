package io.github.dummiejava.dummie.generator.field;

import io.github.dummiejava.dummie.cache.Constant;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import java.lang.reflect.Field;
import java.util.UUID;

public abstract class FieldValueGenerator {

  private final Class<?>[] fieldTypes;

  protected FieldValueGenerator(Class<?>... fieldTypes) {
    this.fieldTypes = fieldTypes;
  }

  public Object generate(DataGenerator dataGenerator, Field field, GenerationStrategy strategy) {
    return generate(dataGenerator, field.getType(), field.getName(), strategy);
  }

  public Object generate(DataGenerator dataGenerator, Class<?> fieldType, String fieldName, GenerationStrategy strategy) {
    switch (strategy) {
      case RANDOM:
        return randomGenerator(dataGenerator, fieldType, fieldName);
      case DEFAULT:
      default:
        return defaultGenerator(dataGenerator, fieldType, fieldName);
    }
  }

  protected String generateKeyValue(GenerationStrategy strategy) {
    return strategy == GenerationStrategy.RANDOM ? UUID.randomUUID().toString() : Constant.DEFAULT_STRING_VALUE;
  }

  protected Object defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return null;
  }

  protected Object randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return null;
  }

  public boolean isMatchType(Class<?> targetFieldType) {
    for (Class<?> fieldType : fieldTypes) {
      if (targetFieldType.isAssignableFrom(fieldType)) {
        return true;
      }
    }
    return false;
  }
}
