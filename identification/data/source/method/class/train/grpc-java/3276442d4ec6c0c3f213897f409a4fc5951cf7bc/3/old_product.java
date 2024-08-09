final void setStream(ClientStream stream) {
    synchronized (this) {
      // If realStream != null, then either setStream() or cancel() has been called.
      if (realStream != null) {
        return;
      }
      realStream = checkNotNull(stream, "stream");
    }

    drainPendingCalls();
  }