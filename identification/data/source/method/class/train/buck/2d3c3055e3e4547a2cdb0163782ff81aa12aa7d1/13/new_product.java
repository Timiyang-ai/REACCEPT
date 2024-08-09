public static Collection<CxxPreprocessorInput> getTransitiveCxxPreprocessorInput(
      final CxxPlatform cxxPlatform,
      Iterable<? extends BuildRule> inputs,
      final Predicate<Object> traverse) throws NoSuchBuildTargetException {

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
              try {
                deps.putAll(
                    dep.getTransitiveCxxPreprocessorInput(
                        cxxPlatform,
                        HeaderVisibility.PUBLIC));
              } catch (NoSuchBuildTargetException e) {
                throw new ClosureException(e);
              }
              return ImmutableSet.of();
            }
            return traverse.apply(rule) ? rule.getDeps() : ImmutableSet.<BuildRule>of();
          }
        };
    try {
      visitor.start();
    } catch (ClosureException e) {
      throw (NoSuchBuildTargetException) e.getException();
    }


    // Grab the cxx preprocessor inputs and return them.
    return deps.values();
  }