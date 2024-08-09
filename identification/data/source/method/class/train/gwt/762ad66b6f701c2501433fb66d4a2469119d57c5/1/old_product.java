public final boolean isTrue(TreeLogger logger, PropertyOracle propertyOracle,
      TypeOracle typeOracle, String testType) throws UnableToCompleteException {

    boolean logDebug = logger.isLoggable(TreeLogger.DEBUG);

    if (logDebug) {
      String startMsg = getEvalBeforeMessage(testType);
      logger = logger.branch(TreeLogger.DEBUG, startMsg, null);
    }

    boolean result = doEval(logger, propertyOracle, typeOracle, testType);

    if (logDebug) {
      String afterMsg = getEvalAfterMessage(testType, result);
      logger.log(TreeLogger.DEBUG, afterMsg, null);
    }

    return result;
  }