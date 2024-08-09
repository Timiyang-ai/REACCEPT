public final void setListener(ServerStreamListener listener) {
    this.listener = Preconditions.checkNotNull(listener, "listener");

    // Now that the stream has actually been initialized, call the listener's onReady callback if
    // appropriate.
    notifyIfReady();
  }