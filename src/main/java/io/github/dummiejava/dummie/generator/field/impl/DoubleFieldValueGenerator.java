package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.util.Random;

public class DoubleFieldValueGenerator extends FieldValueGenerator {

  public DoubleFieldValueGenerator() {
    super(Double.class, double.class);
  }

  @Override
  protected Double defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return 0d;
  }

  @Override
  protected Double randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return new Random().nextDouble();
  }
}
