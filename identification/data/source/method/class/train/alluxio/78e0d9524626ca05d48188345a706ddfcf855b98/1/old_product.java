public void close() {
    if (isOpen()) {
      mClosed = true;
      mRequestObserver.onCompleted();
    }
  }