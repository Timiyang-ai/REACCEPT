public final void setListener(ServerStreamListener listener) {
      Preconditions.checkState(this.listener == null, "setListener should be called only once");
      this.listener = Preconditions.checkNotNull(listener, "listener");
    }