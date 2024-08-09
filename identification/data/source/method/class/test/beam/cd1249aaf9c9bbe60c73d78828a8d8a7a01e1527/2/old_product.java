public void processElement(InputT element) throws Exception {
    if (state == State.FINISHED) {
      throw new IllegalStateException("finishBundle() has already been called");
    }
    if (state == State.UNSTARTED) {
      startBundle();
    }
    try {
      fn.processElement(createProcessContext(fn, element));
    } catch (UserCodeException e) {
      unwrapUserCodeException(e);
    }
  }