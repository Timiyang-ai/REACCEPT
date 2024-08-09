@Deprecated
  public static ImmutableSet<ExecutableElement> getLocalAndInheritedMethods(
      TypeElement type, Elements elementUtils) {
    Overrides overrides = new Overrides.NativeOverrides(elementUtils);
    return getLocalAndInheritedMethods(type, overrides);
  }