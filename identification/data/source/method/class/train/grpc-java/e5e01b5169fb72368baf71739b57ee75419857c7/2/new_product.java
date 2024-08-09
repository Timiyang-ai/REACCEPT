@SuppressWarnings("unchecked")
  public static LbConfig unwrapLoadBalancingConfig(Object lbConfig) {
    Map<String, ?> map;
    try {
      map = (Map<String, ?>) lbConfig;
    } catch (ClassCastException e) {
      ClassCastException ex = new ClassCastException("Invalid type. Config=" + lbConfig);
      ex.initCause(e);
      throw ex;
    }
    if (map.size() != 1) {
      throw new RuntimeException(
          "There are " + map.size() + " fields in a LoadBalancingConfig object. Exactly one"
          + " is expected. Config=" + lbConfig);
    }
    Map.Entry<String, ?> entry = map.entrySet().iterator().next();
    Map<String, ?> configValue;
    try {
      configValue = (Map<String, ?>) entry.getValue();
    } catch (ClassCastException e) {
      ClassCastException ex =
          new ClassCastException("Invalid value type.  value=" + entry.getValue());
      ex.initCause(e);
      throw ex;
    }
    return new LbConfig(entry.getKey(), configValue);
  }