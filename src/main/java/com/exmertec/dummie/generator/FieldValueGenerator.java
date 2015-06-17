package com.exmertec.dummie.generator;

import java.lang.reflect.Field;

import com.exmertec.dummie.cache.DummyCache;

public abstract class FieldValueGenerator {
    private final Class<?>[] fieldTypes;

    protected FieldValueGenerator(Class<?>... fieldTypes) {
        this.fieldTypes = fieldTypes;
    }
    abstract public Object generate(DummyCache cache, Field field);

    abstract public Object generate(DummyCache cache, Class<?> fieldType, String fieldName);

    public boolean isMatchType(Class<?> targetFieldType) {
        for (Class<?> fieldType: fieldTypes) {
            if (targetFieldType.isAssignableFrom(fieldType)) {
                return true;
            }
        }
        return false;
    }
}
