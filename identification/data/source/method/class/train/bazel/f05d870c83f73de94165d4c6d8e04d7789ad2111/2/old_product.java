static BuildOptions replaceFlagValues(BuildOptions original, Map<Label, String> newValues) {
    BuildOptions.Builder result = original.toBuilder();
    for (Map.Entry<String, Object> entry : original.getStarlarkOptions().entrySet()) {
      if (entry.getValue() instanceof FeatureFlagValue) {
        result.removeStarlarkOption(entry.getKey());
      }
    }
    ImmutableMap.Builder<String, Object> newValueObjects = new ImmutableMap.Builder<>();
    for (Map.Entry<Label, String> entry : newValues.entrySet()) {
      newValueObjects.put(entry.getKey().toString(), SetValue.of(entry.getValue()));
    }
    result.addStarlarkOptions(newValueObjects.build());
    BuildOptions builtResult = result.build();
    if (builtResult.contains(ConfigFeatureFlagOptions.class)) {
      builtResult.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent = true;
    }
    return builtResult;
  }