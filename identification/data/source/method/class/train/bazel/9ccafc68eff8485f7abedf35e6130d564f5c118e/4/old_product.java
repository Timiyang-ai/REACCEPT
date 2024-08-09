public BuildOptions trim(Set<Class<? extends FragmentOptions>> optionsClasses) {
    Builder builder = builder();
    for (FragmentOptions options : fragmentOptionsMap.values()) {
      if (optionsClasses.contains(options.getClass())
          || options instanceof BuildConfiguration.Options) {
        builder.add(options);
      }
    }
    return builder.build();
  }