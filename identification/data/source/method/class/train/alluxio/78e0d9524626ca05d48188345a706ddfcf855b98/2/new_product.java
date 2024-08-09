public void cancel() {
    if (isOpen()) {
      LOG.debug("Cancelling stream ({})", mDescription);
      mCanceled = true;
      mRequestObserver.cancel("Request is cancelled by user.", null);
    }
  }