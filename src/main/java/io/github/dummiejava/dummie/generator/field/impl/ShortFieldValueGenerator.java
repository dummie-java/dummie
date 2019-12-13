package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class ShortFieldValueGenerator extends FieldValueGenerator {

    public ShortFieldValueGenerator() {
        super(Short.class, short.class);
    }

    @Override
    protected Short defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0;
    }

    @Override
    protected Short randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return Integer.valueOf(new Random().nextInt()).shortValue();
    }
}
