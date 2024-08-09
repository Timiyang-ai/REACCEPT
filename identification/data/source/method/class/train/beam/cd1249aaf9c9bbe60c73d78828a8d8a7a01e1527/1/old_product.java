public void processElement(InputT element) {
    if (state == State.FINISHED) {
      throw new IllegalStateException("finishBundle() has already been called");
    }
    if (state == State.UNSTARTED) {
      startBundle();
    }
    fnRunner.processElement(WindowedValue.valueInGlobalWindow(element));
  }