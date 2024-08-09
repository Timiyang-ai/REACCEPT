public void send(ReqT request) throws IOException {
    if (mClosed || mCanceled || mClosedFromRemote) {
      LOG.debug("Failed to send request {}: stream is already closed or canceled. ({})",
          request, mDescription);
      return;
    }
    try (LockResource lr = new LockResource(mLock)) {
      checkError();
    }
    mRequestObserver.onNext(request);
  }