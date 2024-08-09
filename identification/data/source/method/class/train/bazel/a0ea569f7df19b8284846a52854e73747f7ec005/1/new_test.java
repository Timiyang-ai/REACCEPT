  protected <T extends SkyValue> EvaluationResult<T> eval(boolean keepGoing, SkyKey... keys)
      throws InterruptedException {
    return eval(keepGoing, ImmutableList.copyOf(keys));
  }