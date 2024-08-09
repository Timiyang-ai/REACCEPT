public final boolean isTrue(TreeLogger logger, DeferredBindingQuery query)
      throws UnableToCompleteException {

    boolean logDebug = logger.isLoggable(TreeLogger.DEBUG);

    if (logDebug) {
      String startMsg = getEvalBeforeMessage(query.getTestType());
      logger = logger.branch(TreeLogger.DEBUG, startMsg, null);
    }

    boolean result = doEval(logger, query);

    if (logDebug) {
      String afterMsg = getEvalAfterMessage(query.getTestType(), result);
      logger.log(TreeLogger.DEBUG, afterMsg, null);
    }

    return result;
  }