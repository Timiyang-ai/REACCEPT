public static Dependency withTransitionAndAspects(
      Label label, Attribute.Transition transition, AspectCollection aspects) {
    return new ConfigurationTransitionDependency(label, transition, aspects);
  }