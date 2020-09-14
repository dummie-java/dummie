package io.github.dummiejava.dummie.configuration;

import io.github.dummiejava.dummie.cache.DataCache;
import io.github.dummiejava.dummie.cache.impl.KeyValueDataCache;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.data.impl.DefaultGenerator;
import io.github.dummiejava.dummie.generator.data.impl.LevelGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Configuration {

  private final List<FieldValueGenerator> fieldValueGenerators;
  private final Set<String> randomFieldKeys;
  private final Set<Class<?>> randomFieldTypes;
  private final Map<String, Object> keyOverrides;
  private final Map<Class<?>, Object> typeOverrides;

  private Integer limit;
  private CycleLogic cycleLogic;
  private Class<? extends DataCache> dataCache;
  private GenerationStrategy generationStrategy;
  private Class<DataGenerator> dataGenerator;

  public Configuration() {
    this(CycleLogic.CYCLE, GenerationStrategy.DEFAULT, KeyValueDataCache.class);
  }

  public Configuration(CycleLogic cycleLogic, GenerationStrategy strategy, Class<? extends DataCache> dataCache) {
    this(cycleLogic, strategy, dataCache, null);
  }

  public Configuration(CycleLogic cycleLogic, GenerationStrategy strategy, Class<? extends DataCache> dataCache, Class<DataGenerator> dataGenerator) {
    setCycleLogic(cycleLogic);
    setGenerationStrategy(strategy);
    setDataCache(dataCache);
    setDataGenerator(dataGenerator);
    this.fieldValueGenerators = new ArrayList<>();
    this.randomFieldKeys = new HashSet<>();
    this.randomFieldTypes = new HashSet<>();
    this.typeOverrides = new HashMap<>();
    this.keyOverrides = new HashMap<>();
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public void setDataCache(Class<? extends DataCache> dataCache) {
    this.dataCache = dataCache;
  }

  public void setGenerationStrategy(GenerationStrategy generationStrategy) {
    this.generationStrategy = generationStrategy;
  }


  public void setCycleLogic(CycleLogic cycleLogic) {
    this.cycleLogic = cycleLogic;
    if (cycleLogic == CycleLogic.LEVEL) { // set default limit value
      setLimit(2);
    }
  }

  public Configuration setDataGenerator(Class<DataGenerator> dataGenerator) {
    this.dataGenerator = dataGenerator;
    return this;
  }

  public Configuration valueGenerator(FieldValueGenerator fieldValueGenerator) {
    this.fieldValueGenerators.add(fieldValueGenerator);
    return this;
  }

  public <E> Configuration override(String key, E value) {
    keyOverrides.put(key, value);
    return this;
  }

  public <E> Configuration override(Class<E> clazz, E value) {
    typeOverrides.put(clazz, value);
    return this;
  }

  public Configuration random(Class<?> clazz) {
    randomFieldTypes.add(clazz);
    return this;
  }

  public Configuration random(String key) {
    randomFieldKeys.add(key);
    return this;
  }

  private DataCache getDataCache() throws IllegalAccessException, InstantiationException {
    DataCache dataCache = this.dataCache.newInstance();
    typeOverrides.forEach((key, value) -> dataCache.cacheData(key, value));
    keyOverrides.forEach((key, value) -> dataCache.cacheData(value.getClass(), key, value));
    return dataCache;
  }

  public DataGenerator getDataGenerator() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    if (this.dataGenerator != null) {
      return dataGenerator.getDeclaredConstructor(GenerationStrategy.class, DataCache.class, Set.class, Set.class, List.class)
          .newInstance(this.generationStrategy, getDataGenerator(), randomFieldKeys, randomFieldTypes, fieldValueGenerators);
    }
    switch (this.cycleLogic) {
      case CYCLE:
        return new DefaultGenerator(this.generationStrategy, getDataCache(), randomFieldKeys, randomFieldTypes, fieldValueGenerators);
      case LEVEL:
        return new LevelGenerator(this.generationStrategy, getDataCache(), randomFieldKeys, randomFieldTypes, limit, fieldValueGenerators);
      default:
        throw new IllegalArgumentException();
    }
  }
}
