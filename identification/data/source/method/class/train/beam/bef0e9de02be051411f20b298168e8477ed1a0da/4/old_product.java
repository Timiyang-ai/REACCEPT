public void processTimestampedElement(TimestampedValue<InputT> element) throws Exception {
    checkNotNull(element, "Timestamped element cannot be null");
    checkState(state != State.FINISHED, "finishBundle() has already been called");

    if (state == State.UNSTARTED) {
      startBundle();
    }
    try {
      fn.processElement(createProcessContext(fn, element));
    } catch (UserCodeException e) {
      unwrapUserCodeException(e);
    }
  }