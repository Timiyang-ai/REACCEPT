@Nullable
  public static List<LbConfig> getFallbackPolicyFromXdsConfig(LbConfig xdsConfig) {
    Map<String, Object> map = xdsConfig.getRawConfigValue();
    Object rawFallbackPolicies = map.get(XDS_CONFIG_FALLBACK_POLICY_KEY);
    if (rawFallbackPolicies != null) {
      return unwrapLoadBalancingConfigList(rawFallbackPolicies);
    }
    return null;
  }