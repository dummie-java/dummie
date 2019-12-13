package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class IntegerFieldValueGenerator extends FieldValueGenerator {

    public IntegerFieldValueGenerator() {
        super(Integer.class, int.class);
    }

    @Override
    protected Integer defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0;
    }

    @Override
    protected Integer randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextInt();
    }
}
