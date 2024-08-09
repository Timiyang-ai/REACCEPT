public static Collection<CxxPreprocessorInput> getTransitiveCxxPreprocessorInput(
      final CxxPlatform cxxPlatform,
      Iterable<? extends BuildRule> inputs,
      final Predicate<Object> traverse) {

    // We don't really care about the order we get back here, since headers shouldn't
    // conflict.  However, we want something that's deterministic, so sort by build
    // target.
    final Map<BuildTarget, CxxPreprocessorInput> deps = Maps.newLinkedHashMap();

    // Build up the map of all C/C++ preprocessable dependencies.
    AbstractBreadthFirstTraversal<BuildRule> visitor =
        new AbstractBreadthFirstTraversal<BuildRule>(inputs) {
          @Override
          public ImmutableSet<BuildRule> visit(BuildRule rule) {
            if (rule instanceof CxxPreprocessorDep) {
              CxxPreprocessorDep dep = (CxxPreprocessorDep) rule;
              deps.putAll(
                  dep.getTransitiveCxxPreprocessorInput(cxxPlatform, HeaderVisibility.PUBLIC));
              return ImmutableSet.of();
            }
            return traverse.apply(rule) ? rule.getDeps() : ImmutableSet.<BuildRule>of();
          }
        };
    visitor.start();

    // Grab the cxx preprocessor inputs and return them.
    return deps.values();
  }