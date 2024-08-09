@Nullable
  public static List<LbConfig> getFallbackPolicyFromXdsConfig(LbConfig xdsConfig) {
    Map<String, ?> map = xdsConfig.getRawConfigValue();
    List<?> rawFallbackPolicies = getList(map, XDS_CONFIG_FALLBACK_POLICY_KEY);
    if (rawFallbackPolicies != null) {
      return unwrapLoadBalancingConfigList(checkObjectList(rawFallbackPolicies));
    }
    return null;
  }