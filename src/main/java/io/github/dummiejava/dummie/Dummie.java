package io.github.dummiejava.dummie;

import io.github.dummiejava.dummie.configuration.Configuration;
import io.github.dummiejava.dummie.configuration.CycleLogic;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;

public class Dummie {

  public static <T> T create(Class<T> type) {
    return prepare().create(type);
  }

  public static DummieBuilder prepare() {
    return new DummieBuilder();
  }

  public static DummieBuilder cycleLogic(CycleLogic logic) {
    DummieBuilder factory = new DummieBuilder();
    factory.cycleLogic(logic);
    return factory;
  }

  public static DummieBuilder withConfiguration(Configuration configuration) {
    DummieBuilder factory = new DummieBuilder(configuration);
    return factory;
  }

  public static DummieBuilder withStrategy(GenerationStrategy strategy) {
    DummieBuilder factory = new DummieBuilder();
    factory.withStrategy(strategy);
    return factory;
  }
}
