public static Dependency withConfiguration(Label label, BuildConfiguration configuration) {
    return new ExplicitConfigurationDependency(
        label, configuration,
        AspectCollection.EMPTY,
        ImmutableMap.<AspectDescriptor, BuildConfiguration>of());
  }