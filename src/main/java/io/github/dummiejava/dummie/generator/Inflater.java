package io.github.dummiejava.dummie.generator;

import io.github.dummiejava.dummie.generator.data.DataGenerator;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

public class Inflater {

  private static <T> void inflateFields(T instance, DataGenerator dataGenerator, Class<T> classType) throws
      IllegalAccessException, InvocationTargetException {
    Field[] fields = classType.getDeclaredFields();
    PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();

    for (Field field : fields) {
      if (!propertyUtils.isWriteable(instance, field.getName())) {
        continue;
      }

      Object value = dataGenerator.getData(field);
      BeanUtils.setProperty(instance, field.getName(), value);
    }
  }

  public static <T> void inflateInstance(T instance, DataGenerator cache, Class<T> type) throws
      InvocationTargetException, IllegalAccessException {
    if (type != null) {
      inflateFields(instance, cache, type);
      inflateInstance(instance, cache, type.getSuperclass());
    }
  }
}
