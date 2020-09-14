package io.github.dummiejava.dummie;


import io.github.dummiejava.dummie.cache.DataCache;
import io.github.dummiejava.dummie.configuration.Configuration;
import io.github.dummiejava.dummie.configuration.CycleLogic;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.Inflater;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

public class DummieBuilder {

  private final Configuration configuration;

  public DummieBuilder() {
    configuration = new Configuration();
  }

  public DummieBuilder(Configuration configuration) {
    this.configuration = configuration;
  }

  public DummieBuilder cycleLogic(CycleLogic logic) {
    configuration.setCycleLogic(logic);
    return this;
  }

  public DummieBuilder withFloor(int floor) {
    configuration.setLimit(floor);
    return this;
  }

  public DummieBuilder withStrategy(GenerationStrategy strategy) {
    configuration.setGenerationStrategy(strategy);
    return this;
  }

  public DummieBuilder valueGenerator(FieldValueGenerator fieldValueGenerator) {
    this.configuration.valueGenerator(fieldValueGenerator);
    return this;
  }

  public DummieBuilder random(Class<?> clazz) {
    configuration.random(clazz);
    return this;
  }

  public DummieBuilder random(String key) {
    configuration.random(key);
    return this;
  }

  public <E> DummieBuilder override(String key, E value) {
    configuration.override(key, value);
    return this;
  }

  public <E> DummieBuilder override(Class<E> clazz, E value) {
    configuration.override(clazz, value);
    return this;
  }

  public DummieBuilder withDataCache(Class<? extends DataCache> dataCache) {
    configuration.setDataCache(dataCache);
    return this;
  }

  public <T> T create(Class<T> type) {
    try {
      DataGenerator dataGenerator = configuration.getDataGenerator();
      T instance = (T) dataGenerator.getData(type, "");
      Inflater.inflateInstance(instance, dataGenerator, type);
      return instance;
    } catch (Exception e) {
      throw new DummieException(e);
    }
  }
}
