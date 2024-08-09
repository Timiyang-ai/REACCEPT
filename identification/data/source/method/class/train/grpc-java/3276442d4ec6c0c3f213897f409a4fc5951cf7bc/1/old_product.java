final void setStream(ClientStream stream) {
    synchronized (this) {
      if (error != null || realStream != null) {
        return;
      }
      realStream = checkNotNull(stream, "stream");
      // listener can only be non-null if start has already been called.
      if (listener != null) {
        startStream();
      }
    }
  }