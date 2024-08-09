public static Dependency withConfigurationAndAspects(
      Label label, BuildConfiguration configuration, Set<AspectDescriptor> aspects) {
    ImmutableMap.Builder<AspectDescriptor, BuildConfiguration> aspectBuilder =
        new ImmutableMap.Builder<>();
    for (AspectDescriptor aspect : aspects) {
      aspectBuilder.put(aspect, configuration);
    }
    return new StaticConfigurationDependency(label, configuration, aspectBuilder.build());
  }