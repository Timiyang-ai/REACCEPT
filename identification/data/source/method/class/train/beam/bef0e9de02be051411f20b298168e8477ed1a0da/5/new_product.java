@Deprecated
  public void processTimestampedElement(TimestampedValue<InputT> element) throws Exception {
    checkNotNull(element, "Timestamped element cannot be null");
    processWindowedElement(
        element.getValue(), element.getTimestamp(), GlobalWindow.INSTANCE);
  }