public static Dependency withConfiguredAspects(
      Label label, BuildConfiguration configuration,
      AspectCollection aspects,
      Map<AspectDescriptor, BuildConfiguration> aspectConfigurations) {
    return new ExplicitConfigurationDependency(
        label, configuration, aspects, ImmutableMap.copyOf(aspectConfigurations));
  }