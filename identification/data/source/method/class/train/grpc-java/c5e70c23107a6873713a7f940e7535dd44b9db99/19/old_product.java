@Override
  public void cancel() {
    // Allow phase to go to cancelled regardless of prior phase.
    outboundPhase = Phase.STATUS;
    if (id() != null) {
      // Only send a cancellation to remote side if we have actually been allocated
      // a stream id. i.e. the server side is aware of the stream.
      sendCancel();
    }
  }