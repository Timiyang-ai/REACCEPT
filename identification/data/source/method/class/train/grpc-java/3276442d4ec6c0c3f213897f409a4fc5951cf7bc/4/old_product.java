void setStream(ClientStream stream) {
    synchronized (this) {
      if (error != null) {
        // If there is an error, unstartedStream will be a Noop.
        return;
      }
      checkState(realStream == null, "Stream already created: %s", realStream);
      realStream = checkNotNull(stream, "stream");
      // listener can only be non-null if start has already been called.
      if (listener != null) {
        startStream();
      }
    }
  }