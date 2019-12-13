package io.github.dummiejava.dummie.generator.field.impl;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import io.github.dummiejava.dummie.generator.field.FieldValueGenerator;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;

public class DateTimeFieldValueGenerator extends FieldValueGenerator {

  public DateTimeFieldValueGenerator() {
    super(LocalDateTime.class, ZonedDateTime.class, OffsetDateTime.class);
  }

  @Override
  protected Object defaultGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    if (fieldType.isAssignableFrom(LocalDateTime.class)) {
      return LocalDateTime.now();
    } else if (fieldType.isAssignableFrom(ZonedDateTime.class)) {
      return ZonedDateTime.now();
    } else {
      return OffsetDateTime.now();
    }
  }

  @Override
  protected Object randomGenerator(DataGenerator dataGenerator, Class<?> fieldType, String fieldName) {
    Random random = new Random();
    LocalDateTime localDateTime = LocalDateTime.of(random.nextInt(9999), random.nextInt(12),
        random.nextInt(30), random.nextInt(24), random.nextInt(60));
    if (fieldType.isAssignableFrom(LocalDateTime.class)) {
      return localDateTime;
    } else if (fieldType.isAssignableFrom(ZonedDateTime.class)) {
      return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    } else {
      return OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
    }
  }
}
