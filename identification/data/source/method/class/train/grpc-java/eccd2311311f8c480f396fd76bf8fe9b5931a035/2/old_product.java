@Override
  public void cancel(Status reason) {
    // At least one of them is null.
    ClientStream streamToBeCancelled = startedRealStream;
    ClientStreamListener listenerToBeCalled = null;
    if (streamToBeCancelled == null) {
      synchronized (this) {
        if (realStream != null) {
          // realStream already set. Just cancel it.
          streamToBeCancelled = realStream;
        } else if (error == null) {
          // Neither realStream and error are set. Will set the error and call the listener if
          // it's set.
          error = checkNotNull(reason);
          realStream = NoopClientStream.INSTANCE;
          if (listener != null) {
            // call startStream anyways to drain pending messages.
            startStream();
            listenerToBeCalled = listener;
          }
        }  // else: error already set, do nothing.
      }
    }
    if (listenerToBeCalled != null) {
      Preconditions.checkState(streamToBeCancelled == null, "unexpected streamToBeCancelled");
      listenerToBeCalled.closed(reason, new Metadata());
    }
    if (streamToBeCancelled != null) {
      streamToBeCancelled.cancel(reason);
    }
  }