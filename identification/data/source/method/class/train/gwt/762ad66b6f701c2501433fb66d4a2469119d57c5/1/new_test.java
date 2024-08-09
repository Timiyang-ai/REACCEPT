  private boolean isTrue(Condition cond, String testType)
      throws UnableToCompleteException {
    return cond.isTrue(TreeLogger.NULL, new DeferredBindingQuery(
        propertyOracle, activeLinkerNames, compilationState, testType));
  }