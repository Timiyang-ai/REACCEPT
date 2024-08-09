public final void setListener(ServerStreamListener listener) {
    this.listener = checkNotNull(listener);

    // Now that the stream has actually been initialized, call the listener's onReady callback if
    // appropriate.
    onStreamAllocated();
  }