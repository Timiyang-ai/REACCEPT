@Nullable
  public static List<LbConfig> getChildPolicyFromXdsConfig(LbConfig xdsConfig) {
    Map<String, Object> map = xdsConfig.getRawConfigValue();
    Object rawChildPolicies = map.get(XDS_CONFIG_CHILD_POLICY_KEY);
    if (rawChildPolicies != null) {
      return unwrapLoadBalancingConfigList(rawChildPolicies);
    }
    return null;
  }