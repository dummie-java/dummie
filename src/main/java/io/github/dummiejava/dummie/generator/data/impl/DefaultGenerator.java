package io.github.dummiejava.dummie.generator.data.impl;

import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.CustomTypeFieldValueGenerator;

public class DefaultGenerator extends DataGenerator {

  public DefaultGenerator(GenerationStrategy generationStrategy) {
    super(generationStrategy);
  }

  @Override
  public Object getData(Class<?> dataType, String key) {
    Object value = super.getData(dataType, key);

    try {
      dynamicCacheData(dataType, key, Class.forName(dataType.getName()).cast(value));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return value;
  }

  @Override
  protected FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType) {
    return new CustomTypeFieldValueGenerator(dataType);
  }
}
