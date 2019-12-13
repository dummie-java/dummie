package io.github.dummiejava.dummie;

import io.github.dummiejava.dummie.configuration.CycleLogic;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;

public class Dummie {
    public static <T> T create(Class<T> type) {
        return prepare(type).build();
    }

    public static <T> DummyBuilder<T> prepare(Class<T> type) {
        return new DummyBuilderFactory().prepare(type);
    }

    public static DummyBuilderFactory cycleLogic(CycleLogic logic) {
        DummyBuilderFactory factory = new DummyBuilderFactory();
        factory.cycleLogic(logic);
        return factory;
    }

    public static DummyBuilderFactory withStrategy(GenerationStrategy strategy) {
        DummyBuilderFactory factory = new DummyBuilderFactory();
        factory.withStrategy(strategy);
        return factory;
    }
}
