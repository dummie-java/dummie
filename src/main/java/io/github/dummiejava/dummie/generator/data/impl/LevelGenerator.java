package io.github.dummiejava.dummie.generator.data.impl;

import io.github.dummiejava.dummie.DummieException;
import io.github.dummiejava.dummie.cache.DataCache;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.Inflater;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class LevelGenerator extends DataGenerator {

  private final Integer limit;
  private Integer current;

  public LevelGenerator(GenerationStrategy strategy, DataCache dataCache,
      Set<String> randomFieldKeys, Set<Class<?>> randomFieldTypes, Integer limit, List<FieldValueGenerator> extraGenerators) {
    super(strategy, dataCache, randomFieldKeys, randomFieldTypes, extraGenerators);

    this.limit = limit;
    this.current = 0;
  }

  private boolean isOverFloor() {
    current += 1;
    return current > limit;
  }

  private void downstream() {
    current -= 1;
  }

  @Override
  public Object getData(Field field) {
    Object value = isOverFloor() ? null : super.getData(field);
    downstream();
    return value;
  }

  @Override
  protected FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType) {
    return new FieldValueGenerator() {
      @Override
      public Object generate(DataGenerator dataGenerator, Class fieldType, String fieldName, GenerationStrategy strategy) {
        try {
          Object instance = fieldType.newInstance();
          Inflater.inflateInstance(instance, dataGenerator, fieldType);
          return instance;
        } catch (Exception e) {
          throw new DummieException(e);
        }
      }
    };
  }
}
