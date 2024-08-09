@Nullable
  public static List<LbConfig> getChildPolicyFromXdsConfig(Map<String, ?> rawXdsConfig) {
    List<?> rawChildPolicies = JsonUtil.getList(rawXdsConfig, XDS_CONFIG_CHILD_POLICY_KEY);
    if (rawChildPolicies != null) {
      return unwrapLoadBalancingConfigList(checkObjectList(rawChildPolicies));
    }
    return null;
  }