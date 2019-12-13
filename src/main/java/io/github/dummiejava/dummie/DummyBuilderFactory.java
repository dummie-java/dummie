package io.github.dummiejava.dummie;


import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.data.impl.DefaultGenerator;
import io.github.dummiejava.dummie.generator.data.impl.LevelGenerator;
import io.github.dummiejava.dummie.configuration.Configuration;
import io.github.dummiejava.dummie.configuration.CycleLogic;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;

public class DummyBuilderFactory {
    private Configuration configuration;

    public DummyBuilderFactory() {
        configuration = new Configuration(CycleLogic.CYCLE, GenerationStrategy.DEFAULT);
    }

    public DummyBuilderFactory cycleLogic(CycleLogic logic) {
        configuration.setCycleLogic(logic);
        return this;
    }

    public DummyBuilderFactory withFloor(int floor) {
        configuration.setFloor(floor);
        return this;
    }

    public DummyBuilderFactory withStrategy(GenerationStrategy strategy) {
        configuration.setGenerationStrategy(strategy);
        return this;
    }

    public <T> DummyBuilder<T> prepare(Class<T> type) {
        return new DummyBuilder(type, getDataGenerator());
    }

    public <T> T create(Class<T> type) {
        return prepare(type).build();
    }

    private DataGenerator getDataGenerator() {
        switch (configuration.getCycleLogic()) {
            case CYCLE:
                return new DefaultGenerator(configuration.getGenerationStrategy());
            case LEVEL:
                return new LevelGenerator(configuration.getGenerationStrategy(), configuration.getFloor());
            default:
                throw new IllegalArgumentException();
        }
    }
}
