public static Collection<CxxPreprocessorInput> getTransitiveCxxPreprocessorInput(
      CxxPlatform cxxPlatform,
      ActionGraphBuilder graphBuilder,
      Iterable<? extends BuildRule> inputs) {
    return getTransitiveCxxPreprocessorInput(cxxPlatform, graphBuilder, inputs, x -> false);
  }