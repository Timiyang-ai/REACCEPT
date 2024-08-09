static BuildOptions trimFlagValues(BuildOptions original, Set<Label> availableFlags) {
    // An important performance property of this method is that we don't create a new BuildOptions
    // instance unless we really need one. This particularly saves the expensive cost of
    // BuildOptions.hashCode(). Since this method is called unconditionally over every configured
    // target, this has real observable effect on build analysis time.
    Set<Label> seenFlags = new LinkedHashSet<>();
    Set<Label> flagsToTrim = new LinkedHashSet<>();
    Map<Label, Object> unknownFlagsToAdd = new LinkedHashMap<>();
    boolean changeAllValuesPresentOption = false;

    // What do we need to change?
    original.getStarlarkOptions().entrySet().stream()
        .filter(entry -> entry.getValue() instanceof FeatureFlagValue)
        .forEach(featureFlagEntry -> seenFlags.add(featureFlagEntry.getKey()));
    flagsToTrim.addAll(Sets.difference(seenFlags, availableFlags));
    FeatureFlagValue unknownFlagValue =
        (original.contains(ConfigFeatureFlagOptions.class)
                && original.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent)
            ? DefaultValue.INSTANCE
            : UnknownValue.INSTANCE;
    for (Label unknownFlag : Sets.difference(availableFlags, seenFlags)) {
      unknownFlagsToAdd.put(unknownFlag, unknownFlagValue);
    }
    if (original.contains(ConfigFeatureFlagOptions.class)) {
      changeAllValuesPresentOption =
          original.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent;
    }

    // Nothing changed? Return the original BuildOptions.
    if (flagsToTrim.isEmpty() && unknownFlagsToAdd.isEmpty() && !changeAllValuesPresentOption) {
      return original;
    }

    // Else construct a new one. This should not be the common case.
    BuildOptions.Builder result = original.toBuilder();
    flagsToTrim.forEach(trimmedFlag -> result.removeStarlarkOption(trimmedFlag));
    unknownFlagsToAdd.forEach((flag, value) -> result.addStarlarkOption(flag, value));
    BuildOptions builtResult = result.build();
    if (builtResult.contains(ConfigFeatureFlagOptions.class)) {
      builtResult.get(ConfigFeatureFlagOptions.class).allFeatureFlagValuesArePresent = false;
    }
    return builtResult;
  }