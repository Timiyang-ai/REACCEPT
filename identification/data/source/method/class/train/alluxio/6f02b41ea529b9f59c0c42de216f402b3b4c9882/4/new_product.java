public void send(ReqT request) throws IOException {
    if (mClosed || mCanceled || mClosedFromRemote) {
      throw new CancelledException(formatErrorMessage(
          "Failed to send request %s: stream is already closed or canceled.", request));
    }
    try (LockResource lr = new LockResource(mLock)) {
      checkError();
    }
    mRequestObserver.onNext(request);
  }