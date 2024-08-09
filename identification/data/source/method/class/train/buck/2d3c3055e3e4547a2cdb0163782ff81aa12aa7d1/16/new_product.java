public static Collection<CxxPreprocessorInput> getTransitiveCxxPreprocessorInput(
      CxxPlatform cxxPlatform,
      ActionGraphBuilder graphBuilder,
      Iterable<? extends CxxPreprocessorDep> inputs) {
    // We don't really care about the order we get back here, since headers shouldn't
    // conflict.  However, we want something that's deterministic, so maintain the insertion order.
    Map<BuildTarget, CxxPreprocessorInput> deps = new LinkedHashMap<>();
    for (CxxPreprocessorDep input : inputs) {
      deps.putAll(input.getTransitiveCxxPreprocessorInput(cxxPlatform, graphBuilder));
    }
    return deps.values();
  }