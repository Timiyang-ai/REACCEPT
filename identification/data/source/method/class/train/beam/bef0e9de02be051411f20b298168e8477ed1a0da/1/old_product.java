public void processTimestampedElement(TimestampedValue<InputT> element) throws Exception {
    checkNotNull(element, "Timestamped element cannot be null");
    if (state != State.BUNDLE_STARTED) {
      startBundle();
    }
    try {
      fn.processElement(createProcessContext(fn, element));
    } catch (UserCodeException e) {
      unwrapUserCodeException(e);
    }
  }