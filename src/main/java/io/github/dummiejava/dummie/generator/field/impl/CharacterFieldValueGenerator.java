package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.util.Random;

public class CharacterFieldValueGenerator extends FieldValueGenerator {

  public CharacterFieldValueGenerator() {
    super(Character.class, char.class);
  }

  @Override
  protected Character defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return '\u0000';
  }

  @Override
  protected Character randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    return String.valueOf(new Random().nextInt(128)).charAt(0);
  }
}
