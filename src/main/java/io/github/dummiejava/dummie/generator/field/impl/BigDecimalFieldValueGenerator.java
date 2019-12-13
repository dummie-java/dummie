package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;

import java.math.BigDecimal;
import java.util.Random;

public class BigDecimalFieldValueGenerator extends FieldValueGenerator {
    public BigDecimalFieldValueGenerator() {
        super(BigDecimal.class);
    }

    @Override
    protected BigDecimal defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
        return new BigDecimal(new Random().nextInt());
    }

}
