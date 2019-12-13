package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class BooleanFieldValueGenerator extends FieldValueGenerator {

    public BooleanFieldValueGenerator() {
        super(Boolean.class, boolean.class);
    }

    @Override
    protected Boolean defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return Boolean.FALSE;
    }

    @Override
    protected Boolean randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextBoolean();
    }
}
