package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.DummieException;
import io.github.dummiejava.dummie.generator.Inflater;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

public class CustomTypeFieldValueGenerator<T> extends FieldValueGenerator {

  private final Class<T> type;

  public CustomTypeFieldValueGenerator(Class<T> type) {
    super();
    this.type = type;
  }

  @Override
  public boolean isMatchType(Class<?> targetFieldType) {
    return true;
  }

  @Override
  protected T defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return randomGenerator(dataGenerator, fieldType, fieldName);
  }

  @Override
  protected T randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    try {
      T instance = type.newInstance();

      dataGenerator.dynamicCacheData(fieldType, fieldName, instance);

      Inflater.inflateInstance(instance, dataGenerator, type);
      return instance;
    } catch (Exception e) {
      throw new DummieException(e);
    }
  }
}
