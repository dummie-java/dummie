package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class LongFieldValueGenerator extends FieldValueGenerator {

    public LongFieldValueGenerator() {
        super(Long.class, long.class);
    }

    @Override
    protected Long defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0L;
    }

    @Override
    protected Long randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextLong();
    }
}
