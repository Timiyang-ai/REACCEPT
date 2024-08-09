@Deprecated
  @SafeVarargs
  public final List<OutputT> processBundle(InputT... inputElements) throws Exception {
    return processBundle(Arrays.asList(inputElements));
  }