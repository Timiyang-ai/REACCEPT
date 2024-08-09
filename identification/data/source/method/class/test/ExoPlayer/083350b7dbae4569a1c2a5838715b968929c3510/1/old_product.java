@Override
  public long getRetryDelayMsFor(
      int dataType, long loadDurationMs, IOException exception, int errorCount) {
    return exception instanceof ParserException
        ? C.TIME_UNSET
        : Math.min((errorCount - 1) * 1000, 5000);
  }