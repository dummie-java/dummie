package io.github.dummiejava.dummie.cache.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class KeyValueDataCache extends BasicDataCache {

  private final Map<Class<?>, KeyValueCache> keyValueCachedData;

  public KeyValueDataCache() {
    keyValueCachedData = new HashMap<>();
  }

  @Override
  public <T> void cacheData(Class<T> dataType, final String key, final Object value) {
    Class<?> cacheDataType = normalize(dataType);
    if (!(value == null || cacheDataType.isInstance(value))) {
      throw new ClassCastException(value + " cannot be cast to " + cacheDataType);
    }
    if (!keyValueCachedData.containsKey(cacheDataType)) {
      KeyValueCache cache = new KeyValueCache();
      keyValueCachedData.put(cacheDataType, cache);
    }
    keyValueCachedData.get(cacheDataType).put(key, value);
  }

  @Override
  public <T> T getCachedData(Class<T> dataType, String key) {
    T result = getKeyValueCachedData(dataType, key);
    return result != null ? result : super.getCachedData(dataType, key);
  }

  private <T> T getKeyValueCachedData(Class<T> dataType, String key) {
    Class<?> cacheDataType = normalize(dataType);
    KeyValueCache cache = keyValueCachedData.get(cacheDataType);
    if (cache != null) {
      return (T) cache.get(key);
    }
    return null;
  }

  private class KeyValueCache {

    private final Pattern isRegex = Pattern.compile("[^a-zA-Z0-9]");

    private final Map<String, Object> keyMatchCache;
    private final Map<String, Object> regexMatchCache;

    public KeyValueCache() {
      this.keyMatchCache = new HashMap<>();
      this.regexMatchCache = new HashMap<>();
    }

    public void put(String key, Object value) {
      if (isRegex.matcher(key).find()) {
        regexMatchCache.put(key, value);
      } else {
        keyMatchCache.put(key, value);
      }
    }

    public Object get(String key) {
      if (keyMatchCache.containsKey(key)) {
        return keyMatchCache.get(key);
      }
      return regexMatchCache.entrySet()
          .stream()
          .filter(entry -> Pattern.matches(entry.getKey(), key))
          .findFirst()
          .map(Entry::getValue).orElse(null);
    }
  }
}
