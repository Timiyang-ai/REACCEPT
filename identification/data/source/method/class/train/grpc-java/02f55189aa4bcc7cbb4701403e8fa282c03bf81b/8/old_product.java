@SuppressWarnings("unchecked")
  @Nullable
  public static List<Map<String, Object>> getFallbackPolicyFromXdsConfig(
      Map<String, Object> lbConfig) {
    Object rawEntry = lbConfig.entrySet().iterator().next().getValue();
    if (rawEntry instanceof Map) {
      Map<String, Object> entry = (Map<String, Object>) rawEntry;
      if (entry.containsKey(XDS_CONFIG_FALLBACK_POLICY_KEY)) {
        return (List<Map<String, Object>>) (List<?>) getList(entry, XDS_CONFIG_FALLBACK_POLICY_KEY);
      }
    }
    return null;
  }