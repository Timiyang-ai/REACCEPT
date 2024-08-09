public static Collection<CxxPreprocessorInput> getTransitiveCxxPreprocessorInput(
      CxxPlatform cxxPlatform, Iterable<? extends BuildRule> inputs, Predicate<Object> traverse) {

    // We don't really care about the order we get back here, since headers shouldn't
    // conflict.  However, we want something that's deterministic, so sort by build
    // target.
    Map<BuildTarget, CxxPreprocessorInput> deps = new LinkedHashMap<>();

    // Build up the map of all C/C++ preprocessable dependencies.
    new AbstractBreadthFirstTraversal<BuildRule>(inputs) {
      @Override
      public Iterable<BuildRule> visit(BuildRule rule) {
        if (rule instanceof CxxPreprocessorDep) {
          CxxPreprocessorDep dep = (CxxPreprocessorDep) rule;
          deps.putAll(dep.getTransitiveCxxPreprocessorInput(cxxPlatform));
          return ImmutableSet.of();
        }
        return traverse.test(rule) ? rule.getBuildDeps() : ImmutableSet.of();
      }
    }.start();

    // Grab the cxx preprocessor inputs and return them.
    return deps.values();
  }