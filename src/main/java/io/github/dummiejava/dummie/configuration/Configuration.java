package io.github.dummiejava.dummie.configuration;

import io.github.dummiejava.dummie.cache.DataCache;
import io.github.dummiejava.dummie.generator.data.DataGenerator;

public class Configuration {

  private Integer limit;
  private CycleLogic cycleLogic;
  private DataCache dataCache;
  private GenerationStrategy generationStrategy;
  private DataGenerator DataGenerator;

  public Configuration(CycleLogic cycleLogic, GenerationStrategy strategy, DataCache dataCache) {
    setCycleLogic(cycleLogic);
    setGenerationStrategy(strategy);
    setDataCache(dataCache);
  }

  public Configuration(CycleLogic cycleLogic, GenerationStrategy strategy, DataCache dataCache, DataGenerator dataGenerator) {
    setCycleLogic(cycleLogic);
    setGenerationStrategy(strategy);
    setDataCache(dataCache);
    setDataGenerator(dataGenerator);
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public CycleLogic getCycleLogic() {
    return cycleLogic;
  }

  public void setCycleLogic(CycleLogic cycleLogic) {
    this.cycleLogic = cycleLogic;
    if (cycleLogic == CycleLogic.LEVEL) { // set default limit value
      limit = 2;
    }
  }

  public DataCache getDataCache() {
    return dataCache;
  }

  public void setDataCache(DataCache dataCache) {
    this.dataCache = dataCache;
  }

  public GenerationStrategy getGenerationStrategy() {
    return generationStrategy;
  }

  public void setGenerationStrategy(GenerationStrategy generationStrategy) {
    this.generationStrategy = generationStrategy;
  }

  public io.github.dummiejava.dummie.generator.data.DataGenerator getDataGenerator() {
    return DataGenerator;
  }

  public void setDataGenerator(io.github.dummiejava.dummie.generator.data.DataGenerator dataGenerator) {
    DataGenerator = dataGenerator;
  }
}
