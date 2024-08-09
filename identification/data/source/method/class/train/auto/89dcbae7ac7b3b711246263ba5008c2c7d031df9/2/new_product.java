public static ImmutableSet<ExecutableElement> getLocalAndInheritedMethods(
      TypeElement type, Types typeUtils, Elements elementUtils) {
    return getLocalAndInheritedMethods(type, new ExplicitOverrides(typeUtils));
  }