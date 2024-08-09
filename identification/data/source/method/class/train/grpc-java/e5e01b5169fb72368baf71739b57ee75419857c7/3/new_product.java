public static LbConfig unwrapLoadBalancingConfig(Map<String, ?> lbConfig) {
    if (lbConfig.size() != 1) {
      throw new RuntimeException(
          "There are " + lbConfig.size() + " fields in a LoadBalancingConfig object. Exactly one"
          + " is expected. Config=" + lbConfig);
    }
    String key = lbConfig.entrySet().iterator().next().getKey();
    return new LbConfig(key, JsonUtil.getObject(lbConfig, key));
  }