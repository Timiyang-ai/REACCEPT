  @SafeVarargs
  private static void checkResults(Consumer<ReceiveCommand>... resultSetters) throws Exception {
    RefUpdateUtil.checkResults(newBatchRefUpdate(resultSetters));
  }