@Nullable
  public static List<LbConfig> getChildPolicyFromXdsConfig(LbConfig xdsConfig) {
    Map<String, ?> map = xdsConfig.getRawConfigValue();
    List<?> rawChildPolicies = getList(map, XDS_CONFIG_CHILD_POLICY_KEY);
    if (rawChildPolicies != null) {
      return unwrapLoadBalancingConfigList(checkObjectList(rawChildPolicies));
    }
    return null;
  }