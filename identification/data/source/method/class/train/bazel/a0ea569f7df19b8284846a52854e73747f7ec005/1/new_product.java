private <T extends SkyValue> EvaluationResult<T> constructResult(
      Iterable<SkyKey> skyKeys,
      @Nullable Map<SkyKey, ValueWithMetadata> bubbleErrorInfo,
      boolean catastrophe)
      throws InterruptedException {
    Preconditions.checkState(
        catastrophe == (evaluatorContext.keepGoing() && bubbleErrorInfo != null),
        "Catastrophe not consistent with keepGoing mode and bubbleErrorInfo: %s %s %s %s",
        skyKeys,
        catastrophe,
        evaluatorContext.keepGoing(),
        bubbleErrorInfo);
    EvaluationResult.Builder<T> result = EvaluationResult.builder();
    List<SkyKey> cycleRoots = new ArrayList<>();
    for (SkyKey skyKey : skyKeys) {
      SkyValue unwrappedValue = maybeGetValueFromError(
          skyKey,
          graph.get(null, Reason.PRE_OR_POST_EVALUATION, skyKey),
          bubbleErrorInfo);
      ValueWithMetadata valueWithMetadata =
          unwrappedValue == null ? null : ValueWithMetadata.wrapWithMetadata(unwrappedValue);
      // Cycle checking: if there is a cycle, evaluation cannot progress, therefore,
      // the final values will not be in DONE state when the work runs out.
      if (valueWithMetadata == null) {
        // Don't look for cycles if the build failed for a known reason.
        if (bubbleErrorInfo == null) {
          cycleRoots.add(skyKey);
        }
        continue;
      }
      // Replaying here is necessary for error bubbling and other cases.
      replay(valueWithMetadata);
      SkyValue value = valueWithMetadata.getValue();
      ErrorInfo errorInfo = valueWithMetadata.getErrorInfo();
      Preconditions.checkState(value != null || errorInfo != null, skyKey);
      if (!evaluatorContext.keepGoing() && errorInfo != null) {
        // value will be null here unless the value was already built on a prior keepGoing build.
        result.addError(skyKey, errorInfo);
        continue;
      }
      if (value == null) {
        // Note that we must be in the keepGoing case. Only make this value an error if it doesn't
        // have a value. The error shouldn't matter to the caller since the value succeeded after a
        // fashion.
        result.addError(skyKey, errorInfo);
      } else {
        result.addResult(skyKey, value);
      }
    }
    if (!cycleRoots.isEmpty()) {
      cycleDetector.checkForCycles(cycleRoots, result, evaluatorContext);
    }
    if (catastrophe) {
      // We may not have a top-level node completed. Inform the caller of at least one catastrophic
      // exception that shut down the evaluation so that it has some context.
      for (ValueWithMetadata valueWithMetadata : bubbleErrorInfo.values()) {
        ErrorInfo errorInfo =
            Preconditions.checkNotNull(
                valueWithMetadata.getErrorInfo(),
                "bubbleErrorInfo should have contained element with errorInfo: %s",
                bubbleErrorInfo);
        Preconditions.checkState(
            errorInfo.isCatastrophic(),
            "bubbleErrorInfo should have contained element with catastrophe: %s",
            bubbleErrorInfo);
        result.setCatastrophe(errorInfo.getException());
      }
    }
    EvaluationResult<T> builtResult = result.build();
    Preconditions.checkState(
        bubbleErrorInfo == null || builtResult.hasError(),
        "If an error bubbled up, some top-level node must be in error: %s %s %s",
        bubbleErrorInfo,
        skyKeys,
        builtResult);
    return builtResult;
  }