package com.exmertec.dummie.generator;

import java.lang.reflect.Field;

public interface FieldValueGenerator {
    Object generate(Field field);
}