public static Dependency withConfiguration(Label label, BuildConfiguration configuration) {
    return new StaticConfigurationDependency(
        label, configuration, ImmutableMap.<AspectDescriptor, BuildConfiguration>of());
  }