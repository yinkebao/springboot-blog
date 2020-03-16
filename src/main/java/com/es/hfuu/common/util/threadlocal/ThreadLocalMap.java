package com.es.hfuu.common.util.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ThreadLocalMap
 * @Description
 * @Author ykb
 * @Date 2020/3/15
 */
public class ThreadLocalMap {

  private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = new ThreadLocalMap.MapThreadLocal();

  public static void put(String key, Object value) {
    getContextMap().put(key, value);
  }

  public static Object remove(String key) {
    return getContextMap().remove(key);
  }

  public static Object get(String key) {
    return getContextMap().get(key);
  }

  private static Map<String, Object> getContextMap() {
    return (Map)THREAD_CONTEXT.get();
  }

  public static void remove() {
    getContextMap().clear();
  }

  private ThreadLocalMap() {
  }

  private static class MapThreadLocal extends ThreadLocal<Map<String, Object>> {
    private MapThreadLocal() {
    }

    @Override
    protected Map<String, Object> initialValue() {
      return new HashMap<String, Object>(8) {
        private static final long serialVersionUID = 3637958959138295593L;

        @Override
        public Object put(String key, Object value) {
          return super.put(key, value);
        }
      };
    }
  }
}
