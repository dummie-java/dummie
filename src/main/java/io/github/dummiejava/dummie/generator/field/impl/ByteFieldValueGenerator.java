package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.util.Random;

public class ByteFieldValueGenerator extends FieldValueGenerator {

  public ByteFieldValueGenerator() {
    super(Byte.class, byte.class);
  }

  @Override
  protected Byte defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return (byte) 0;
  }

  @Override
  protected Byte randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    byte[] bytes = new byte[1];
    new Random().nextBytes(bytes);
    return bytes[0];
  }
}
