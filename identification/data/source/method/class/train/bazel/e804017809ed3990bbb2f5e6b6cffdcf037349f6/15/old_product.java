public static Dependency withConfiguredAspects(
      Label label, BuildConfiguration configuration,
      Map<Aspect, BuildConfiguration> aspectConfigurations) {
    return new StaticConfigurationDependency(
        label, configuration, ImmutableMap.copyOf(aspectConfigurations));
  }