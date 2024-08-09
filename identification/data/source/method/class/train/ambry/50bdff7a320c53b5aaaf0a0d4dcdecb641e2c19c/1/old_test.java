  @Override
  public void onCompletion(Long result, Exception exception) {
    if (callbackInvoked.compareAndSet(false, true)) {
      bytesRead = result;
      this.exception = exception;
      latch.countDown();
    } else {
      this.exception = new IllegalStateException("Callback invoked more than once");
    }
  }