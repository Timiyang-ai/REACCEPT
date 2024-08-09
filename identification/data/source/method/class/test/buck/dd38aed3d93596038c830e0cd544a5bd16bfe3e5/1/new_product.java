public static ImmutableSet<JavaLibrary> getClasspathDeps(Iterable<BuildRule> deps) {
    ImmutableSet.Builder<JavaLibrary> classpathDeps = ImmutableSet.builder();
    for (BuildRule dep : deps) {
      if (dep instanceof HasClasspathEntries) {
        classpathDeps.addAll(((HasClasspathEntries) dep).getTransitiveClasspathDeps());
      }
    }
    return classpathDeps.build();
  }