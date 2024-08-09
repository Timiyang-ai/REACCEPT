public static ImmutableSet<JavaLibrary> getClasspathDeps(Iterable<BuildRule> deps) {
    ImmutableSet.Builder<JavaLibrary> classpathDeps = ImmutableSet.builder();
    for (BuildRule dep : deps) {
      if (dep instanceof JavaLibrary) {
        classpathDeps.addAll(((JavaLibrary) dep).getTransitiveClasspathDeps());
      }
    }
    return classpathDeps.build();
  }