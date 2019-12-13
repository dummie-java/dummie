package io.github.dummiejava.dummie.generator.data.impl;

import io.github.dummiejava.dummie.DummieException;
import io.github.dummiejava.dummie.configuration.GenerationStrategy;
import io.github.dummiejava.dummie.generator.Inflater;
import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.lang.reflect.Field;

public class LevelGenerator extends DataGenerator {

    private Integer floor;
    private Integer currentFloor;

    public LevelGenerator(GenerationStrategy strategy, Integer floor) {
        super(strategy);

        this.floor = floor;
        this.currentFloor = 0;
    }

     private boolean isOverFloor() {
        currentFloor += 1;
        return currentFloor > floor;
    }

    private void downstream() {
      currentFloor -= 1;
    }

    public Object getData(Field field) {
        Object value = isOverFloor() ? null : super.getData(field);
        downstream();
        return value;
    }

    @Override
    protected FieldValueGenerator getDefaultFieldValueGenerator(Class<?> dataType) {
        return new FieldValueGenerator() {
            @Override
            public Object generate(DataGenerator dataGenerator, Field field) {
                return generate(dataGenerator, field.getType(), field.getName());
            }

            @Override
            public Object generate(DataGenerator dataGenerator, Class fieldType, String fieldName) {
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
