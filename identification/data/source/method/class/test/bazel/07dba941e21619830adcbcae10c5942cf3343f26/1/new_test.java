  private OrderedSetMultimap<DependencyKind, Dependency> dependentNodeMap(
      String targetName, NativeAspectClass aspect) throws Exception {
    Target target =
        packageManager.getTarget(reporter, Label.parseAbsolute(targetName, ImmutableMap.of()));
    OrderedSetMultimap<DependencyKind, Dependency> prerequisiteMap =
        dependencyResolver.dependentNodeMap(
            new TargetAndConfiguration(target, getTargetConfiguration()),
            getHostConfiguration(),
            aspect != null ? Aspect.forNative(aspect) : null,
            ImmutableMap.of(),
            /*toolchainContext=*/ null,
            /*trimmingTransitionFactory=*/ null);

    return prerequisiteMap;
  }