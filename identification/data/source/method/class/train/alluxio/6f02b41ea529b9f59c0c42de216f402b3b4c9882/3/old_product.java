public void send(ReqT request, long timeoutMs) throws IOException {
    if (mClosed || mCanceled || mClosedFromRemote) {
      throw new CanceledException(formatErrorMessage(
          "Failed to send request %s: stream is already closed or canceled.", request));
    }
    try (LockResource lr = new LockResource(mLock)) {
      while (true) {
        checkError();
        if (mRequestObserver.isReady()) {
          break;
        }
        try {
          if (!mReadyOrFailed.await(timeoutMs, TimeUnit.MILLISECONDS)) {
            throw new DeadlineExceededException(
                formatErrorMessage("Timeout sending request %s after %dms.", request, timeoutMs));
          }
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new CanceledException(formatErrorMessage(
              "Failed to send request %s: interrupted while waiting for server.", request), e);
        }
      }
    }
    mRequestObserver.onNext(request);
  }