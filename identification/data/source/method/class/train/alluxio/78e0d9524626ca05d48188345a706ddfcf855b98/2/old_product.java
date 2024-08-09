public void cancel() {
    if (isOpen()) {
      mCanceled = true;
      mRequestObserver.cancel("Request is cancelled by user.", null);
    }
  }