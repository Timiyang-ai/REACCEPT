@Nullable
  public static List<LbConfig> getFallbackPolicyFromXdsConfig(Map<String, ?> rawXdsConfig) {
    List<?> rawFallbackPolicies = JsonUtil.getList(rawXdsConfig, XDS_CONFIG_FALLBACK_POLICY_KEY);
    if (rawFallbackPolicies != null) {
      return unwrapLoadBalancingConfigList(JsonUtil.checkObjectList(rawFallbackPolicies));
    }
    return null;
  }