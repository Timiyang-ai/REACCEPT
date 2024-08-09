public static Dependency withTransitionAndAspects(
      Label label, Attribute.Transition transition, Iterable<AspectDescriptor> aspects) {
    return new DynamicConfigurationDependency(label, transition, ImmutableSet.copyOf(aspects));
  }