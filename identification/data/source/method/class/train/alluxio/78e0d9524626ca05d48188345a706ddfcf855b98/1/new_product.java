public void close() {
    if (isOpen()) {
      LOG.debug("Closing stream ({})", mDescription);
      mClosed = true;
      mRequestObserver.onCompleted();
    }
  }