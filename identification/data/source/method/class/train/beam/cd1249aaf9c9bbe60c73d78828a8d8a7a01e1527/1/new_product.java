public void processElement(InputT element) throws Exception {
    if (state == State.FINISHED) {
      throw new IllegalStateException("finishBundle() has already been called");
    }
    if (state == State.UNSTARTED) {
      startBundle();
    }
    fn.processElement(createProcessContext(fn, element));
  }