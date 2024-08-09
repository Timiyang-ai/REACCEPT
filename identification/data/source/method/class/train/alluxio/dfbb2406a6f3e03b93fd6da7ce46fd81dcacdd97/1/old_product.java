public ResT receive(long timeoutMs) throws IOException {
    if (mCompleted) {
      return null;
    }
    if (mCanceled) {
      throw new CanceledException(formatErrorMessage("Stream is already canceled."));
    }
    try {
      Object response = mResponses.poll(timeoutMs, TimeUnit.MILLISECONDS);
      if (response == null) {
        throw new DeadlineExceededException(
            formatErrorMessage("Timeout waiting for response after %dms.", timeoutMs));
      }
      if (response == mResponseObserver) {
        mCompleted = true;
        return null;
      }
      checkError();
      return (ResT) response;
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new CanceledException(formatErrorMessage("Interrupted while waiting for response."), e);
    }
  }