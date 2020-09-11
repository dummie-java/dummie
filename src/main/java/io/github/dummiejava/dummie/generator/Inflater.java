package io.github.dummiejava.dummie.generator;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;

public class Inflater {

  private static <T> void inflateFields(T instance, DataGenerator dataGenerator, Class<T> classType) throws
      IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    Field[] fields = classType.getDeclaredFields();

    for (Field field : fields) {
      if (!PropertyUtils.isWriteable(instance, field.getName())) {
        continue;
      }

      Object value = dataGenerator.getData(field);
      PropertyUtils.setProperty(instance, field.getName(), value);
    }
  }

  public static <T> void inflateInstance(T instance, DataGenerator dataGenerator, Class<T> type) throws
      InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    if (type != null) {
      inflateFields(instance, dataGenerator, type);
      inflateInstance(instance, dataGenerator, type.getSuperclass());
    }
  }
}
