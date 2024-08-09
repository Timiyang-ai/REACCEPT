public static Dependency withConfigurationAndAspects(
      Label label, BuildConfiguration configuration, Set<Aspect> aspects) {
    ImmutableMap.Builder<Aspect, BuildConfiguration> aspectBuilder = new ImmutableMap.Builder<>();
    for (Aspect aspect : aspects) {
      aspectBuilder.put(aspect, configuration);
    }
    return new StaticConfigurationDependency(label, configuration, aspectBuilder.build());
  }