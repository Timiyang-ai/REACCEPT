public static Dependency withConfiguration(Label label, BuildConfiguration configuration) {
    return new StaticConfigurationDependency(
        label, configuration,
        AspectCollection.EMPTY,
        ImmutableMap.<AspectDescriptor, BuildConfiguration>of());
  }