static BuildOptions trimFlagValues(BuildOptions original, Set<Label> availableFlags) {
    BuildOptions.Builder result = original.toBuilder();
    ImmutableSet.Builder<Label> seenFlagsBuilder = new ImmutableSet.Builder<>();
    for (Map.Entry<Label, Object> entry : original.getStarlarkOptions().entrySet()) {
      if (entry.getValue() instanceof FeatureFlagValue) {
        seenFlagsBuilder.add(entry.getKey());
      }
    }
    ImmutableSet<Label> seenFlags = seenFlagsBuilder.build();
    for (Label trimmedFlag : Sets.difference(seenFlags, availableFlags)) {
      result.removeStarlarkOption(trimmedFlag);
    }
    FeatureFlagValue unknownFlagValue =
        (original.contains(ConfigFeatureFlagOptions.class)
                && original.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent)
            ? DefaultValue.INSTANCE
            : UnknownValue.INSTANCE;
    for (Label unknownFlag : Sets.difference(availableFlags, seenFlags)) {
      result.addStarlarkOption(unknownFlag, unknownFlagValue);
    }
    BuildOptions builtResult = result.build();
    if (builtResult.contains(ConfigFeatureFlagOptions.class)) {
      builtResult.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent = false;
    }
    return builtResult;
  }