public static OptionsDiffForReconstruction diffForReconstruction(
      BuildOptions first, BuildOptions second) {
    OptionsDiff diff = diff(first, second);
    if (diff.areSame()) {
      return OptionsDiffForReconstruction.getEmpty(first.fingerprint, second.computeChecksum());
    }
    LinkedHashMap<Class<? extends FragmentOptions>, Map<String, Object>> differingOptions =
        new LinkedHashMap<>(diff.differingOptions.keySet().size());
    for (Class<? extends FragmentOptions> clazz :
        diff.differingOptions
            .keySet()
            .stream()
            .sorted(lexicalFragmentOptionsComparator)
            .collect(Collectors.toList())) {
      Collection<OptionDefinition> fields = diff.differingOptions.get(clazz);
      LinkedHashMap<String, Object> valueMap = new LinkedHashMap<>(fields.size());
      for (OptionDefinition optionDefinition :
          fields
              .stream()
              .sorted(Comparator.comparing(o -> o.getField().getName()))
              .collect(Collectors.toList())) {
        Object secondValue;
        try {
          secondValue = Iterables.getOnlyElement(diff.second.get(optionDefinition));
        } catch (IllegalArgumentException e) {
          // TODO(janakr): Currently this exception should never be thrown since diff is never
          // constructed using the diff method that takes in a preexisting OptionsDiff. If this
          // changes, add a test verifying this error catching works properly.
          throw new IllegalStateException(
              "OptionsDiffForReconstruction can only handle a single first BuildOptions and a "
                  + "single second BuildOptions and has encountered multiple second BuildOptions");
        }
        valueMap.put(optionDefinition.getField().getName(), secondValue);
      }
      differingOptions.put(clazz, valueMap);
    }
    first.maybeInitializeFingerprintAndHashCode();
    return new OptionsDiffForReconstruction(
        differingOptions,
        diff.extraFirstFragments
            .stream()
            .sorted(lexicalFragmentOptionsComparator)
            .collect(ImmutableSet.toImmutableSet()),
        diff.extraSecondFragments
            .stream()
            .sorted(Comparator.comparing(o -> o.getClass().getName()))
            .collect(ImmutableList.toImmutableList()),
        first.fingerprint,
        second.computeChecksum(),
        diff.skylarkSecond,
        diff.extraSkylarkOptionsFirst,
        diff.extraSkylarkOptionsSecond);
  }