@VisibleForTesting
  protected static CxxPreprocessorInput getTransitiveCxxPreprocessorInput(
      Iterable<? extends BuildRule> inputs) {

    // We don't really care about the order we get back here, since headers shouldn't
    // conflict.  However, we want something that's deterministic, so sort by build
    // target.
    final Map<BuildTarget, CxxPreprocessorInput> deps = Maps.newTreeMap();

    // Build up the map of all C/C++ preprocessable dependencies.
    AbstractDependencyVisitor visitor = new AbstractDependencyVisitor(inputs) {
      @Override
      public ImmutableSet<BuildRule> visit(BuildRule rule) {
        if (rule instanceof CxxPreprocessorDep) {
          CxxPreprocessorDep dep = (CxxPreprocessorDep) rule;
          Preconditions.checkState(!deps.containsKey(rule.getBuildTarget()));
          deps.put(rule.getBuildTarget(), dep.getCxxPreprocessorInput());
          return rule.getDeps();
        } else {
          return ImmutableSet.of();
        }
      }
    };
    visitor.start();

    // Grab the cxx preprocessor inputs and return them.
    return CxxPreprocessorInput.concat(deps.values());
  }