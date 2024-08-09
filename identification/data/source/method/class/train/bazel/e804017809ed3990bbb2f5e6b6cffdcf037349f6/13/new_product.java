public static Dependency withTransitionAndAspects(
      Label label, Transition transition, AspectCollection aspects) {
    return new ConfigurationTransitionDependency(label, transition, aspects);
  }