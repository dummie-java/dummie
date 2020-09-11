package io.github.dummiejava.dummie;


import io.github.dummiejava.dummie.cache.DataCache;
import io.github.dummiejava.dummie.cache.impl.KeyValueDataCache;
import io.github.dummiejava.dummie.configuration.Configuration;
import io.github.dummiejava.dummie.configuration.CycleLogic;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.data.impl.DefaultGenerator;
import io.github.dummiejava.dummie.generator.data.impl.LevelGenerator;

public class DummyBuilderFactory {

  private final Configuration configuration;

  public DummyBuilderFactory() {
    configuration = new Configuration(CycleLogic.CYCLE, GenerationStrategy.DEFAULT, new KeyValueDataCache());
  }

  public DummyBuilderFactory(Configuration configuration) {
    this.configuration = configuration;
  }

  public DummyBuilderFactory cycleLogic(CycleLogic logic) {
    configuration.setCycleLogic(logic);
    return this;
  }

  public DummyBuilderFactory withFloor(int floor) {
    configuration.setLimit(floor);
    return this;
  }

  public DummyBuilderFactory withStrategy(GenerationStrategy strategy) {
    configuration.setGenerationStrategy(strategy);
    return this;
  }

  public DummyBuilderFactory withDataCache(DataCache dataCache) {
    configuration.setDataCache(dataCache);
    return this;
  }

  public <T> DummyInstanceBuilder<T> prepare(Class<T> type) {
    return new DummyInstanceBuilder(type, getDataGenerator());
  }

  public <T> T create(Class<T> type) {
    return prepare(type).build();
  }

  private DataGenerator getDataGenerator() {
    if (configuration.getDataGenerator() != null) {
      return configuration.getDataGenerator();
    }
    switch (configuration.getCycleLogic()) {
      case CYCLE:
        return new DefaultGenerator(configuration.getGenerationStrategy(), configuration.getDataCache());
      case LEVEL:
        return new LevelGenerator(configuration.getGenerationStrategy(), configuration.getDataCache(), configuration.getLimit());
      default:
        throw new IllegalArgumentException();
    }
  }
}
