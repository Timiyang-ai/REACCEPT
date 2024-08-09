@SuppressWarnings("unchecked")
  @Nullable
  public static List<Map<String, Object>> getChildPolicyFromXdsConfig(
      Map<String, Object> xdsConfig) {
    Object rawEntry = xdsConfig.entrySet().iterator().next().getValue();
    if (rawEntry instanceof Map) {
      Map<String, Object> entry = (Map<String, Object>) rawEntry;
      if (entry.containsKey(XDS_CONFIG_CHILD_POLICY_KEY)) {
        return (List<Map<String, Object>>) (List<?>) getList(entry, XDS_CONFIG_CHILD_POLICY_KEY);
      }
    }
    return null;
  }