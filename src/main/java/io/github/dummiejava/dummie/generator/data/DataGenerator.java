package io.github.dummiejava.dummie.generator.data;

import io.github.dummiejava.dummie.cache.DataCache;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.BigDecimalFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.BooleanFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.ByteFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.CharacterFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.DateTimeFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.DoubleFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.EnumFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.FloatFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.IntegerFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.ListFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.LongFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.MapFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.SetFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.ShortFieldValueGenerator;
import io.github.dummiejava.dummie.generator.field.impl.StringFieldValueGenerator;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class DataGenerator {

  private final List<FieldValueGenerator> generators;
  private final DataCache dataCache;
  private final GenerationStrategy strategy;
  private final Set<String> randomFieldKeys;
  private final Set<Class<?>> randomFieldTypes;

  public DataGenerator(GenerationStrategy strategy, DataCache dataCache, Set<String> randomFieldKeys,
      Set<Class<?>> randomFieldTypes, List<FieldValueGenerator> extraGenerators) {
    this.dataCache = dataCache;
    this.strategy = strategy;

    this.generators = new ArrayList<>();
    this.randomFieldKeys = randomFieldKeys;
    this.randomFieldTypes = randomFieldTypes;

    addDefaultGenerators();
    this.generators.addAll(extraGenerators);
  }

  private void addDefaultGenerators() {
    generators.add(new StringFieldValueGenerator());
    generators.add(new ListFieldValueGenerator());
    generators.add(new MapFieldValueGenerator());
    generators.add(new SetFieldValueGenerator());
    generators.add(new BooleanFieldValueGenerator());
    generators.add(new ByteFieldValueGenerator());
    generators.add(new CharacterFieldValueGenerator());
    generators.add(new DoubleFieldValueGenerator());
    generators.add(new FloatFieldValueGenerator());
    generators.add(new IntegerFieldValueGenerator());
    generators.add(new LongFieldValueGenerator());
    generators.add(new ShortFieldValueGenerator());
    generators.add(new EnumFieldValueGenerator());
    generators.add(new BigDecimalFieldValueGenerator());
    generators.add(new DateTimeFieldValueGenerator());
  }

  public Object getData(Field field) {
    Class<?> fieldType = field.getType();
    Object value = dataCache.getCachedData(fieldType, field.getName());
    if (value == null) {
      FieldValueGenerator generator = getCachedGenerator(fieldType);
      if (generator != null) {
        value = generator.generate(this, field, getStrategy(fieldType, field.getName()));
      }
    }
    return value;
  }

  public Object getData(Class<?> dataType, String key) {
    Object value = dataCache.getCachedData(dataType, key);
    if (value == null) {
      FieldValueGenerator generator = getCachedGenerator(dataType);
      if (generator != null) {
        value = generator.generate(this, dataType, key, getStrategy(dataType, key));
      }
    }
    return value;
  }

  public <T> void dynamicCacheData(Class<T> dataType, String key, Object value) {
    if (getStrategy(dataType, key) == GenerationStrategy.DEFAULT) {
      dataCache.cacheData(dataType, key, value);
    }
  }

  protected GenerationStrategy getStrategy(Class<?> dataType, String key) {
    return randomFieldTypes.contains(dataType) || randomFieldKeys.contains(key) ?
        GenerationStrategy.RANDOM : strategy;
  }

  private FieldValueGenerator getCachedGenerator(Class<?> dataType) {
    for (FieldValueGenerator generator : generators) {
      if (generator.isMatchType(dataType)) {
        return generator;
      }
    }

    return getDefaultFieldValueGenerator(dataType);
  }

  protected abstract FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType);
}
