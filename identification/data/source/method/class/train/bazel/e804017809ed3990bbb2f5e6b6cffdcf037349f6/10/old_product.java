public static Dependency withTransitionAndAspects(
      Label label, Attribute.Transition transition, Set<Aspect> aspects) {
    return new DynamicConfigurationDependency(label, transition, ImmutableSet.copyOf(aspects));
  }