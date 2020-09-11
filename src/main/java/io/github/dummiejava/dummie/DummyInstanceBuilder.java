package io.github.dummiejava.dummie;

import io.github.dummiejava.dummie.generator.Inflater;
import io.github.dummiejava.dummie.generator.data.DataGenerator;

public class DummyInstanceBuilder<T> {

  private final Class<T> type;
  private final DataGenerator dataGenerator;

  public DummyInstanceBuilder(Class<T> type, DataGenerator dataGenerator) {
    this.type = type;
    this.dataGenerator = dataGenerator;
  }

  public T build() {
    try {
      T instance = (T) dataGenerator.getData(type, "");
      Inflater.inflateInstance(instance, dataGenerator, type);
      return instance;
    } catch (Exception e) {
      throw new DummieException(e);
    }
  }

  public <E> DummyInstanceBuilder<T> override(String key, E value) {
    dataGenerator.cacheData(value.getClass(), key, value);
    return this;
  }

  public <E> DummyInstanceBuilder<T> override(Class<E> clazz, E value) {
    dataGenerator.cacheData(clazz, value);
    return this;
  }

  public <E> DummyInstanceBuilder<T> random(Class<E> clazz) {
    dataGenerator.random(clazz);
    return this;
  }

  public <E> DummyInstanceBuilder<T> random(String key) {
    dataGenerator.random(key);
    return this;
  }
}
