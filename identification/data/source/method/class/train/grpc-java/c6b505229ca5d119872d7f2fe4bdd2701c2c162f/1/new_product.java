@SuppressWarnings("unchecked")
  public static List<LbConfig> unwrapLoadBalancingConfigList(List<Map<String, ?>> list) {
    ArrayList<LbConfig> result = new ArrayList<>();
    for (Map<String, ?> rawChildPolicy : list) {
      result.add(unwrapLoadBalancingConfig(rawChildPolicy));
    }
    return Collections.unmodifiableList(result);
  }