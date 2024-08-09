public static ImmutableList<HasAndroidResourceDeps> getAndroidResourceDeps(
      Collection<BuildRule> rules) {
    // Build up the dependency graph that was traversed to find the AndroidResourceRules.
    final MutableDirectedGraph<BuildRule> mutableGraph = new MutableDirectedGraph<>();
    UnsortedAndroidResourceDeps.Callback callback = new UnsortedAndroidResourceDeps.Callback() {
      @Override
      public void onRuleVisited(BuildRule rule, ImmutableSet<BuildRule> depsToVisit) {
        mutableGraph.addNode(rule);
        for (BuildRule dep : depsToVisit) {
          mutableGraph.addEdge(rule, dep);
        }
      }
    };

    final Set<HasAndroidResourceDeps> androidResourceDeps =
        UnsortedAndroidResourceDeps.createFrom(rules, Optional.of(callback))
            .getResourceDeps();

    // Now that we have the transitive set of AndroidResourceRules, we need to return them in
    // topologically sorted order. This is critical because the order in which -S flags are passed
    // to aapt is significant and must be consistent.
    Predicate<BuildRule> inclusionPredicate = new Predicate<BuildRule>() {
      @Override
      public boolean apply(BuildRule rule) {
        return androidResourceDeps.contains(rule);
      }
    };
    ImmutableList<BuildRule> sortedAndroidResourceRules = TopologicalSort.sort(mutableGraph,
        inclusionPredicate);

    // TopologicalSort.sort() returns rules in leaves-first order, which is the opposite of what we
    // want, so we must reverse the list and cast BuildRules to AndroidResourceRules.
    return ImmutableList.copyOf(
        Iterables.transform(
            sortedAndroidResourceRules.reverse(),
            CAST_TO_ANDROID_RESOURCE_RULE));
  }