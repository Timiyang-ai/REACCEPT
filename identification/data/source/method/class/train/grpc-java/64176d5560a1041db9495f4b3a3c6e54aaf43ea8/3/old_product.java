public final void setListener(ServerStreamListener listener) {
    this.listener = Preconditions.checkNotNull(listener, "listener");
  }