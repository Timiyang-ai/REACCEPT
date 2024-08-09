@Deprecated
  public void processElement(InputT element) throws Exception {
    processTimestampedElement(TimestampedValue.atMinimumTimestamp(element));
  }