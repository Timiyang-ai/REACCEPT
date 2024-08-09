private ImmutableList<HasAndroidResourceDeps> getAndroidResourceDeps(DependencyGraph graph) {
    BuildRule self = graph.findBuildRuleByTarget(buildTarget);
    return UberRDotJavaUtil.getAndroidResourceDeps(self);
  }