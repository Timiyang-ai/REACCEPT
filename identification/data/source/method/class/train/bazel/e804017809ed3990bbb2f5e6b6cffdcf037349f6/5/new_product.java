public static Dependency withConfigurationAndAspects(
      Label label, BuildConfiguration configuration, AspectCollection aspects) {
    ImmutableMap.Builder<AspectDescriptor, BuildConfiguration> aspectBuilder =
        new ImmutableMap.Builder<>();
    for (AspectDescriptor aspect : aspects.getAllAspects()) {
      aspectBuilder.put(aspect, configuration);
    }
    return new StaticConfigurationDependency(label, configuration, aspects, aspectBuilder.build());
  }