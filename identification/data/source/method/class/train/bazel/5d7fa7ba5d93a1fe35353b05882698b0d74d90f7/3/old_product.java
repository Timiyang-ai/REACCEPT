public static OptionsDiffForReconstruction diffForReconstruction(
      BuildOptions first, BuildOptions second) {
    OptionsDiff diff = diff(first, second);
    if (diff.areSame()) {
      return OptionsDiffForReconstruction.getEmpty(first.fingerprint);
    }
    HashMap<Class<? extends FragmentOptions>, Map<String, Object>> differingOptions =
        new HashMap<>(diff.differingOptions.keySet().size());
    for (Class<? extends FragmentOptions> clazz : diff.differingOptions.keySet()) {
      Collection<OptionDefinition> fields = diff.differingOptions.get(clazz);
      HashMap<String, Object> valueMap = new HashMap<>(fields.size());
      for (OptionDefinition optionDefinition : fields) {
        Object secondValue = diff.second.get(optionDefinition);
        valueMap.put(optionDefinition.getField().getName(), secondValue);
      }
      differingOptions.put(clazz, valueMap);
    }
    first.maybeInitializeFingerprintAndHashCode();
    return new OptionsDiffForReconstruction(
        differingOptions,
        ImmutableSet.copyOf(diff.extraFirstFragments),
        ImmutableList.copyOf(diff.extraSecondFragments),
        first.fingerprint);
  }