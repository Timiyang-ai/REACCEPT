public static CxxPreprocessorInput getTransitiveCxxPreprocessorInput(
      Iterable<? extends BuildRule> inputs) {

    // Build up a graph of the inputs and their transitive dependencies.
    final MutableDirectedGraph<BuildRule> graph = new MutableDirectedGraph<>();
    AbstractDependencyVisitor visitor = new AbstractDependencyVisitor(inputs) {
      @Override
      public ImmutableSet<BuildRule> visit(BuildRule rule) {
        graph.addNode(rule);
        for (BuildRule dep : rule.getDeps()) {
          graph.addEdge(rule, dep);
        }
        return rule.getDeps();
      }
    };
    visitor.start();

    // Grab the cxx preprocessor inputs and return them.
    return CxxPreprocessorInput.concat(
        FluentIterable
            // We don't really care about the order we get back here, since headers shouldn't
            // conflict.  However, we want something that's deterministic, so sort by build
            // target.
            .from(ImmutableSortedSet.orderedBy(HasBuildTarget.BUILD_TARGET_COMPARATOR)
                .addAll(graph.getNodes())
                .build())
            .filter(CxxPreprocessorDep.class)
            .transform(CxxPreprocessorDep.GET_CXX_PREPROCESSOR_INPUT)
            .toList());
  }