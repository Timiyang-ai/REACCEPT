public static Dependency withTransitionAndAspects(
      Label label, ConfigurationTransition transition, AspectCollection aspects) {
    return new ConfigurationTransitionDependency(label, transition, aspects);
  }