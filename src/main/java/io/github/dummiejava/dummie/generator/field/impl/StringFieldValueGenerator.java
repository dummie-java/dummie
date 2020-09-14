package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.util.UUID;

public class StringFieldValueGenerator extends FieldValueGenerator {

  public StringFieldValueGenerator() {
    super(String.class);
  }

  @Override
  protected String defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return fieldName;
  }

  @Override
  protected String randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return UUID.randomUUID().toString();
  }
}
