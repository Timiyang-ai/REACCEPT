@Nullable
  public static List<LbConfig> getFallbackPolicyFromXdsConfig(Map<String, ?> rawXdsConfig) {
    List<?> rawFallbackPolicies = getList(rawXdsConfig, XDS_CONFIG_FALLBACK_POLICY_KEY);
    if (rawFallbackPolicies != null) {
      return unwrapLoadBalancingConfigList(checkObjectList(rawFallbackPolicies));
    }
    return null;
  }