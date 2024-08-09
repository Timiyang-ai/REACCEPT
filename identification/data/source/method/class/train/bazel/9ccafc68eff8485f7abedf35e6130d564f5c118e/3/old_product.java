public BuildOptions trim(Set<Class<? extends FragmentOptions>> optionsClasses) {
    List<FragmentOptions> retainedOptions =
        Lists.newArrayListWithExpectedSize(optionsClasses.size() + 1);
    for (FragmentOptions options : fragmentOptionsMap.values()) {
      if (optionsClasses.contains(options.getClass())
          // TODO(bazel-team): make this non-hacky while not requiring BuildConfiguration access
          // to BuildOptions.
          || options.getClass().getName().endsWith("BuildConfiguration$Options")) {
        retainedOptions.add(options);
      }
    }
    if (retainedOptions.size() == fragmentOptionsMap.size()) {
      return this; // Nothing to trim.
    }
    Builder builder = builder();
    for (FragmentOptions options : retainedOptions) {
      builder.addFragmentOptions(options);
    }
    return builder.addStarlarkOptions(skylarkOptionsMap).build();
  }