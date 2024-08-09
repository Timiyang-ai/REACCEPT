public static Dependency withConfiguredAspects(
      Label label, BuildConfiguration configuration,
      AspectCollection aspects,
      Map<AspectDescriptor, BuildConfiguration> aspectConfigurations) {
    return new StaticConfigurationDependency(
        label, configuration, aspects, ImmutableMap.copyOf(aspectConfigurations));
  }