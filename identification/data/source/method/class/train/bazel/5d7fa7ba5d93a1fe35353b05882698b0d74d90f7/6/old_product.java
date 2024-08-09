public static OptionsDiffForReconstruction diffForReconstruction(
      BuildOptions first, BuildOptions second) {
    OptionsDiff diff = diff(first, second);
    if (diff.areSame()) {
      return OptionsDiffForReconstruction.EMPTY;
    }
    ImmutableMap.Builder<Class<? extends FragmentOptions>, ImmutableMap<String, Object>>
        differingOptionsBuilder =
            ImmutableMap.builderWithExpectedSize(diff.differingOptions.keySet().size());
    for (Class<? extends FragmentOptions> clazz : diff.differingOptions.keySet()) {
      Collection<OptionDefinition> fields = diff.differingOptions.get(clazz);
      ImmutableMap.Builder<String, Object> valuesMapBuilder =
          ImmutableMap.builderWithExpectedSize(fields.size());
      for (OptionDefinition optionDefinition : fields) {
        valuesMapBuilder.put(
            optionDefinition.getField().getName(), diff.second.get(optionDefinition));
      }
      differingOptionsBuilder.put(clazz, valuesMapBuilder.build());
    }
    return new OptionsDiffForReconstruction(
        differingOptionsBuilder.build(),
        ImmutableSet.copyOf(diff.extraFirstFragments),
        ImmutableList.copyOf(diff.extraSecondFragments));
  }