public static ImmutableSet<ExecutableElement> getLocalAndInheritedMethods(
      TypeElement type, Types typeUtils, Elements elementUtils) {
    // TODO(emcmanus): detect if the Types and Elements are the javac ones, and use
    //   NativeOverrides if so. We may need to adjust the logic further to avoid the bug
    //   tested for by MoreElementsTest.getLocalAndInheritedMethods_DaggerBug.
    Overrides overrides = new Overrides.ExplicitOverrides(typeUtils, elementUtils);
    return getLocalAndInheritedMethods(type, overrides);
  }