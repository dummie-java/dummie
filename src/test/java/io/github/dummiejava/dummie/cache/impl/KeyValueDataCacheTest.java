package io.github.dummiejava.dummie.cache.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class KeyValueDataCacheTest {

  @Test
  public void should_success_match_customized_key() {
    KeyValueDataCache dataCache = new KeyValueDataCache();
    String key = "targetKey";
    String value = "targetValue";
    dataCache.cacheData(String.class, key, value);

    String data = dataCache.getCachedData(String.class, key);

    assertEquals(value, data);
  }

  @Test
  public void should_success_match_customized_regex_key() {
    KeyValueDataCache dataCache = new KeyValueDataCache();
    String key = ".*Key";
    String value = "targetValue";
    dataCache.cacheData(String.class, key, value);

    String data = dataCache.getCachedData(String.class, "customerKey");

    assertEquals(value, data);
  }

  @Test
  public void should_not_match_cache_when_key_neither_match_regex_and_key() {
    KeyValueDataCache dataCache = new KeyValueDataCache();
    String key = ".*Key";
    String value = "targetValue";
    dataCache.cacheData(String.class, key, value);

    String data = dataCache.getCachedData(String.class, "aaa");

    assertNull(data);
  }
}