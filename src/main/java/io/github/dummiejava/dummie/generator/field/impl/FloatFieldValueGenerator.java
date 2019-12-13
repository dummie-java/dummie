package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

import java.util.Random;

public class FloatFieldValueGenerator extends FieldValueGenerator {

    public FloatFieldValueGenerator() {
        super(Float.class, float.class);
    }

    @Override
    protected Float defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return 0F;
    }

    @Override
    protected Float randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new Random().nextFloat();
    }
}
