static BuildOptions trimFlagValues(BuildOptions original, Set<Label> availableFlags) {
    BuildOptions.Builder result = original.toBuilder();
    ImmutableSet.Builder<String> seenFlagsBuilder = new ImmutableSet.Builder<>();
    for (Map.Entry<String, Object> entry : original.getStarlarkOptions().entrySet()) {
      if (entry.getValue() instanceof FeatureFlagValue) {
        seenFlagsBuilder.add(entry.getKey());
      }
    }
    // TODO(juliexxia): remove this hack-around when the SBC map is Label, Object
    ImmutableSet<String> availableFlagsAsStrings =
        availableFlags.stream().map(Label::toString).collect(toImmutableSet());
    ImmutableSet<String> seenFlags = seenFlagsBuilder.build();
    for (String trimmedFlag : Sets.difference(seenFlags, availableFlagsAsStrings)) {
      result.removeStarlarkOption(trimmedFlag);
    }
    FeatureFlagValue unknownFlagValue =
        (original.contains(ConfigFeatureFlagOptions.class)
                && original.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent)
            ? DefaultValue.INSTANCE
            : UnknownValue.INSTANCE;
    for (String unknownFlag : Sets.difference(availableFlagsAsStrings, seenFlags)) {
      result.addStarlarkOption(unknownFlag, unknownFlagValue);
    }
    BuildOptions builtResult = result.build();
    if (builtResult.contains(ConfigFeatureFlagOptions.class)) {
      builtResult.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent = false;
    }
    return builtResult;
  }