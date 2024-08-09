@SuppressWarnings("unchecked")
  public static List<LbConfig> unwrapLoadBalancingConfigList(Object listObject) {
    List<?> list;
    try {
      list = (List<?>) listObject;
    } catch (ClassCastException e) {
      ClassCastException ex = new ClassCastException("List expected, but is " + listObject);
      ex.initCause(e);
      throw ex;
    }
    ArrayList<LbConfig> result = new ArrayList<>();
    for (Object rawChildPolicy : list) {
      result.add(unwrapLoadBalancingConfig(rawChildPolicy));
    }
    return Collections.unmodifiableList(result);
  }