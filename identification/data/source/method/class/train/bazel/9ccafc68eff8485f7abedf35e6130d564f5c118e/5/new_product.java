public BuildOptions trim(Set<Class<? extends FragmentOptions>> optionsClasses) {
    Builder builder = builder();
    for (FragmentOptions options : fragmentOptionsMap.values()) {
      if (optionsClasses.contains(options.getClass())
          // TODO(bazel-team): make this non-hacky while not requiring BuildConfiguration access
          // to BuildOptions.
          || options.toString().contains("BuildConfiguration$Options")) {
        builder.add(options);
      }
    }
    return builder.addSkylarkOptions(skylarkOptionsMap).build();
  }